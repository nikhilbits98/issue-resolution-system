package org.nikhil.issue.resolution.facade.impl;

import lombok.NonNull;
import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.dtos.IssueSearchRequestDto;
import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.models.Issue;
import org.nikhil.issue.resolution.facade.IssueResolutionFacade;
import org.nikhil.issue.resolution.services.AgentService;
import org.nikhil.issue.resolution.services.IssueService;
import org.nikhil.issue.resolution.services.strategy.AssignIssueStrategyFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueResolutionFacadeImpl implements IssueResolutionFacade {

    private final IssueService issueService;
    private final AgentService agentService;
    private final AssignIssueStrategyFactory assignIssueStrategyFactory;

    public IssueResolutionFacadeImpl(IssueService issueService, AgentService agentService, AssignIssueStrategyFactory assignIssueStrategyFactory) {
        this.issueService = issueService;
        this.agentService = agentService;
        this.assignIssueStrategyFactory = assignIssueStrategyFactory;
    }

    @Override
    public List<Issue> getIssues(IssueSearchRequestDto issueSearchRequestDto) {
        return issueService.getIssuesByCustomerEmailAndOrIssueType(issueSearchRequestDto.getEmail(),issueSearchRequestDto.getIssueType());
    }

    @Override
    public String updateIssue(String issueId, IssueStatus status, String resolution) {
        return issueService.updateIssue(issueId,status,resolution).getId();
    }

    @Override
    public String resolveIssue(String issueId, String resolution) {
        Issue issue = issueService.resolveIssue(issueId, resolution);
        if(issue.getAgent() != null){
            Agent agent = issue.getAgent();
            Issue nextIssue = agent.getWaitingIssues().poll();
            agent.assignIssue(nextIssue);
        }
        return issue.getId();
    }

    @Override
    public Map<String,List<String>> viewAgentsWorkHistory() {
        List<Agent> allAgents = agentService.getAllAgents();
        Map<String,List<String>> agentToIssueHistory = new HashMap<>();
        for(Agent agent: allAgents){
            agentToIssueHistory.put(agent.getId(), agent.getHistoryIssues().stream().map(Issue::getId).collect(Collectors.toList()));
        }
        return agentToIssueHistory;
    }

    @Override
    public void assignIssue(String issueId) {
        Issue issue = issueService.getIssueById(issueId);
        List<Agent> allAgents = agentService.getAllAgents();
        Agent agentToBeAssigned = assignIssueStrategyFactory.getIssueTypeAssignStrategy().getAgentForIssueAssignment(issue,allAgents);
        agentToBeAssigned.assignIssue(issue);
        issue.assignAgent(agentToBeAssigned);
    }
}
