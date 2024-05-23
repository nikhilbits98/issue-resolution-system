package org.nikhil.issue.resolution.services.strategy;

import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.models.Issue;

import java.util.List;

public interface AssignIssueStrategy {

    Agent getAgentForIssueAssignment(Issue issue, List<Agent> allAgents);
}
