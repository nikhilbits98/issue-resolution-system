package org.nikhil.issue.resolution.services;

import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.repository.AgentRepository;

import java.util.List;

public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent createAgent(String agentEmail, String agentName, List<IssueType> issueTypes){
        return agentRepository.create(agentEmail, agentName, issueTypes);
    }

    public List<Agent> getAllAgents(){
        return agentRepository.getAll();
    }
}
