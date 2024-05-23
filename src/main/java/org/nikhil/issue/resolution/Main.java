package org.nikhil.issue.resolution;

import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.dtos.IssueSearchRequestDto;
import org.nikhil.issue.resolution.facade.IssueResolutionFacade;
import org.nikhil.issue.resolution.facade.impl.IssueResolutionFacadeImpl;
import org.nikhil.issue.resolution.repository.AgentRepository;
import org.nikhil.issue.resolution.repository.impl.InMemoryAgentRepository;
import org.nikhil.issue.resolution.repository.impl.InMemoryIssueRepository;
import org.nikhil.issue.resolution.services.AgentService;
import org.nikhil.issue.resolution.services.IssueService;
import org.nikhil.issue.resolution.services.strategy.AssignIssueStrategyFactory;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    }
}