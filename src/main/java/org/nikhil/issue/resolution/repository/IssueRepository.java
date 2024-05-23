package org.nikhil.issue.resolution.repository;

import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.models.Issue;

import java.util.List;

public interface IssueRepository {
    Issue create(String transactionId, IssueType issueType, String subject, String description, String email);
    Issue getById(String issueId);
    List<Issue> getByCustomerEmail(String email);
    List<Issue> getByIssueType(IssueType issueType);
    List<Issue> getByIssueTypeAndCustomerEmail(IssueType issueType, String email);
    List<Issue> getAllIssues();
}
