package org.nikhil.issue.resolution.models;

import lombok.Getter;
import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

@Getter
public class Agent {
    private final String id;
    private final String name;
    private final String email;
    private final List<IssueType> issueTypes;

    private transient Issue assignedIssue;
    private transient Queue<Issue> waitingIssues;
    private transient List<Issue> historyIssues;

    public Agent(String id, String name, String email, List<IssueType> issueTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.issueTypes = issueTypes;
        this.waitingIssues = new LinkedList<>();
        this.historyIssues = new LinkedList<>();
    }

    public void assignIssue(Issue issue){
        if(this.assignedIssue == null || this.assignedIssue.getStatus() == IssueStatus.RESOLVED){
            this.assignedIssue = issue;
            this.historyIssues.add(issue);
        } else {
            this.waitingIssues.add(issue);
        }
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", issueTypes=" + issueTypes +
                '}';
    }
}
