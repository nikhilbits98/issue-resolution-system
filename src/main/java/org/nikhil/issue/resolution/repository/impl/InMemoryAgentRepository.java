package org.nikhil.issue.resolution.repository.impl;

import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.repository.AgentRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAgentRepository implements AgentRepository {

    private static Map<String, Agent> agents;

    public InMemoryAgentRepository() {
        agents = new HashMap<>();
    }

    @Override
    public Agent create(String agentEmail, String agentName, List<IssueType> issueTypes) {
        Agent agent = new Agent("A" + (int)(agents.size() + 1),agentName, agentEmail, issueTypes);
        agents.put(agent.getId(), agent);
        return agent;
    }

    @Override
    public List<Agent> getAll() {
        return new ArrayList<>(agents.values());
    }
}
