package org.nikhil.issue.resolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.dtos.IssueSearchRequestDto;
import org.nikhil.issue.resolution.facade.IssueResolutionFacade;
import org.nikhil.issue.resolution.facade.impl.IssueResolutionFacadeImpl;
import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.models.Issue;
import org.nikhil.issue.resolution.repository.impl.InMemoryAgentRepository;
import org.nikhil.issue.resolution.repository.impl.InMemoryIssueRepository;
import org.nikhil.issue.resolution.services.AgentService;
import org.nikhil.issue.resolution.services.IssueService;
import org.nikhil.issue.resolution.services.strategy.AssignIssueStrategyFactory;

import java.util.Arrays;

class IssueResolutionTest {

    private IssueResolutionFacade issueResolutionFacade;
    private AgentService agentService;
    private IssueService issueService;
    private AssignIssueStrategyFactory assignIssueStrategyFactory;

    @BeforeEach
    public void setup(){
        agentService = new AgentService(new InMemoryAgentRepository());
        issueService = new IssueService(new InMemoryIssueRepository());
        assignIssueStrategyFactory = new AssignIssueStrategyFactory();
        issueResolutionFacade = new IssueResolutionFacadeImpl(issueService,agentService,assignIssueStrategyFactory);
    }

    @Test
    public void integrationTest(){
        Issue issue1 = issueService.createIssue("T1", IssueType.PAYMENT_RELATED, "Payment Failed", "My payment failed but money is debited", "testUser1@test.com");
        Issue issue2 = issueService.createIssue( "T2", IssueType.MUTUAL_FUND_RELATED, "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com");
        Issue issue3 = issueService.createIssue( "T3", IssueType.PAYMENT_RELATED, "Payment Failed", "My payment failed but money is debited", "testUser2@test.com");
        Agent agent1 = agentService.createAgent("agent1@test.com", "Agent 1", Arrays.asList(IssueType.PAYMENT_RELATED, IssueType.GOLD_RELATED));
        Agent agent2 = agentService.createAgent("agent2@test.com", "Agent 2", Arrays.asList(IssueType.PAYMENT_RELATED, IssueType.MUTUAL_FUND_RELATED));
        issueResolutionFacade.assignIssue(issue1.getId());
        System.out.println("ASSIGNED:"+ issueService.getIssueById(issue1.getId()));
        issueResolutionFacade.assignIssue(issue2.getId());
        System.out.println("ASSIGNED:"+ issueService.getIssueById(issue2.getId()));
        issueResolutionFacade.assignIssue(issue3.getId());
        System.out.println("ASSIGNED:"+ issueService.getIssueById(issue3.getId()));
        System.out.println(issueResolutionFacade.getIssues(IssueSearchRequestDto.builder().email("testUser1@test.com").build()));
        System.out.println(issueResolutionFacade.getIssues(IssueSearchRequestDto.builder().email("testUser2@test.com").build()));
        System.out.println(issueResolutionFacade.getIssues(IssueSearchRequestDto.builder().email("testUser2@test.com").issueType(IssueType.PAYMENT_RELATED).build()));
        System.out.println(issueResolutionFacade.getIssues(IssueSearchRequestDto.builder().issueType(IssueType.PAYMENT_RELATED).build()));
        issueResolutionFacade.updateIssue(issue1.getId(), IssueStatus.IN_PROGRESS, "Waiting for payment confirmation");
        System.out.println("UPDATED:"+ issueService.getIssueById(issue1.getId()));
        System.out.println("----------------------------");
        System.out.println("Agent1: Assigned: " + agent1.getAssignedIssue() + ", Waiting: " + agent1.getWaitingIssues() + ", History: " + agent1.getHistoryIssues());
        issueResolutionFacade.resolveIssue(issue1.getId(), "PaymentFailed debited amount will get reversed");
        System.out.println("Agent1: Assigned: " + agent1.getAssignedIssue() + ", Waiting: " + agent1.getWaitingIssues() + ", History: " + agent1.getHistoryIssues());
        System.out.println("----------------------------");
        System.out.println("RESOLVED:"+ issueService.getIssueById(issue1.getId()));
        System.out.println(issueResolutionFacade.viewAgentsWorkHistory());
        System.out.println("Agent1: Assigned: " + agent1.getAssignedIssue() + ", Waiting: " + agent1.getWaitingIssues() + ", History: " + agent1.getHistoryIssues());
        System.out.println("Agent2: Assigned: " + agent2.getAssignedIssue() + ", Waiting: " + agent2.getWaitingIssues() + ", History: " + agent2.getHistoryIssues());
    }

}