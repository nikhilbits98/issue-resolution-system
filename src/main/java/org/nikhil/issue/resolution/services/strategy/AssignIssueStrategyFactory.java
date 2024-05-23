package org.nikhil.issue.resolution.services.strategy;

import org.nikhil.issue.resolution.services.strategy.impl.IssueTypeAssignStrategy;

public class AssignIssueStrategyFactory {

    private final AssignIssueStrategy issueTypeAssignStrategy;

    public AssignIssueStrategyFactory() {
        this.issueTypeAssignStrategy = new IssueTypeAssignStrategy();
    }

    public AssignIssueStrategy getIssueTypeAssignStrategy() {
        // Add more strategies here if required in future.
        return issueTypeAssignStrategy;
    }
}
