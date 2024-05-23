package org.nikhil.issue.resolution.repository;

import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.models.Agent;

import java.util.List;

public interface AgentRepository {
    Agent create(String agentEmail, String agentName, List<IssueType> issueTypes);
    List<Agent> getAll();
}
