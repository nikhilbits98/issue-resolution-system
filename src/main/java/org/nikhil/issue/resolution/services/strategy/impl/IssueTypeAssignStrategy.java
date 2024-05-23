package org.nikhil.issue.resolution.services.strategy.impl;

import org.nikhil.issue.resolution.exceptions.AssignException;
import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.models.Issue;
import org.nikhil.issue.resolution.services.AgentService;
import org.nikhil.issue.resolution.services.strategy.AssignIssueStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class IssueTypeAssignStrategy implements AssignIssueStrategy {

    public IssueTypeAssignStrategy() {
    }

    @Override
    public Agent getAgentForIssueAssignment(Issue issue, List<Agent> allAgents) {
        List<Agent> agents =
                allAgents
                        .stream()
                        .filter(agent -> agent.getIssueTypes().contains(issue.getIssueType()))
                        .collect(Collectors.toList());
        if(agents.isEmpty()){
            throw new AssignException("No agents available to assign issue");
        }
        Agent suitableAgent = null;
        int assignedTaskCount = Integer.MAX_VALUE;
        for(Agent agent : agents){
            if(agent.getAssignedIssue() == null){
                suitableAgent = agent;
                assignedTaskCount = 0;
                break;
            }
            if(agent.getWaitingIssues().size() < assignedTaskCount){
                suitableAgent = agent;
                assignedTaskCount = agent.getWaitingIssues().size();
            }
        }
        return suitableAgent;
    }

}
