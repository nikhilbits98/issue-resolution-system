package org.nikhil.issue.resolution.services;

import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.exceptions.BadRequestException;
import org.nikhil.issue.resolution.models.Issue;
import org.nikhil.issue.resolution.repository.IssueRepository;

import java.util.List;

public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        return issueRepository.create(transactionId, issueType, subject, description, email);
    }

    public Issue updateIssue(String issueId, IssueStatus status, String resolution){
        Issue issue = issueRepository.getById(issueId);
        issue.updateStatus(status);
        issue.addResolutionRemarks(resolution);
        return issue;
    }

    public List<Issue> getIssuesByCustomerEmailAndOrIssueType(String email, IssueType issueType){
        if(email != null && issueType != null){
            return issueRepository.getByIssueTypeAndCustomerEmail(issueType, email);
        } else if(email != null){
            return issueRepository.getByCustomerEmail(email);
        } else if(issueType != null){
            return issueRepository.getByIssueType(issueType);
        } else {
            throw new BadRequestException("Either email or issueType must be provided");
        }
    }

    public Issue resolveIssue(String issueId, String resolution){
        Issue issue = issueRepository.getById(issueId);
        issue.updateStatus(IssueStatus.RESOLVED);
        issue.addResolutionRemarks(resolution);
        return issue;
    }

    public Issue getIssueById(String issueId){
        return issueRepository.getById(issueId);
    }
}
