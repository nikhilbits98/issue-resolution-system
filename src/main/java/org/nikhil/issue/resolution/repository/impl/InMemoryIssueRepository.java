package org.nikhil.issue.resolution.repository.impl;

import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.exceptions.NotFoundException;
import org.nikhil.issue.resolution.models.Issue;
import org.nikhil.issue.resolution.repository.IssueRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryIssueRepository implements IssueRepository {

    private static Map<String, Issue> issuesById;
    private static Map<String, List<Issue>> issuesByEmail;
    private static Map<IssueType, List<Issue>> issuesByType;
    private static Map<IssueStatus, List<Issue>> issuesByStatus;

    public InMemoryIssueRepository() {
        issuesById = new HashMap<>();
        issuesByEmail = new HashMap<>();
        issuesByType = new HashMap<>();
        issuesByStatus = new HashMap<>();
    }

    @Override
    public Issue create(String transactionId, IssueType issueType, String subject, String description, String email) {
        Issue issue = new Issue("I" + (int)(issuesById.size() + 1), transactionId, issueType, subject, description, email);
        issuesById.put(issue.getId(), issue);
        issuesByEmail.computeIfAbsent(email, k -> new ArrayList<>()).add(issue);
        issuesByType.computeIfAbsent(issueType, k -> new ArrayList<>()).add(issue);
        issuesByStatus.computeIfAbsent(issue.getStatus(), k -> new ArrayList<>()).add(issue);
        return issue;
    }

    @Override
    public Issue getById(String issueId) {
        return Optional.ofNullable(issuesById.get(issueId)).orElseThrow(() -> new NotFoundException("Issue not found"));
    }

    @Override
    public List<Issue> getByCustomerEmail(String email) {
        if(!issuesByEmail.containsKey(email)){
            return new ArrayList<>();
        }
        return issuesByEmail.get(email);
    }

    @Override
    public List<Issue> getByIssueType(IssueType issueType) {
        if(!issuesByType.containsKey(issueType)){
            return new ArrayList<>();
        }
        return issuesByType.get(issueType);
    }

    @Override
    public List<Issue> getByIssueTypeAndCustomerEmail(IssueType issueType, String email) {
        List<Issue> issues = new ArrayList<>();
        if(issuesByEmail.containsKey(email)){
            issues = issuesByEmail.get(email);
        }
        return issues.stream().filter(issue -> issue.getIssueType() == issueType).collect(Collectors.toList());
    }

    @Override
    public List<Issue> getAllIssues() {
        return new ArrayList<>(issuesById.values());
    }
}
