package org.nikhil.issue.resolution.models;

import lombok.Getter;
import lombok.ToString;
import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.services.AgentService;

import java.util.UUID;

@Getter
public class Issue {
    private final String id;
    private final String transactionId;
    private final IssueType issueType;
    private final String subject;
    private final String description;
    private final String customerEmail;
    private IssueStatus status;
    private String resolutionRemarks;
    private Agent agent;

    public Issue(String id, String transactionId, IssueType issueType, String subject, String description, String customerEmail) {
        this.id = id;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.customerEmail = customerEmail;
        this.status = IssueStatus.OPEN;
    }

    public void updateStatus(IssueStatus status){
        this.status = status;
    }

    public void addResolutionRemarks(String resolutionRemarks){
        this.resolutionRemarks = resolutionRemarks;
    }

    public void assignAgent(Agent agent){
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", issueType=" + issueType +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", status=" + status +
                ", resolutionRemarks='" + resolutionRemarks + '\'' +
                ", agent=" + agent +
                '}';
    }
}
