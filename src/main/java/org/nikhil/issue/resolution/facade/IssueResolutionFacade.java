package org.nikhil.issue.resolution.facade;

import org.nikhil.issue.resolution.constants.IssueStatus;
import org.nikhil.issue.resolution.constants.IssueType;
import org.nikhil.issue.resolution.dtos.IssueSearchRequestDto;
import org.nikhil.issue.resolution.models.Agent;
import org.nikhil.issue.resolution.models.Issue;

import java.util.List;
import java.util.Map;

public interface IssueResolutionFacade {

    void assignIssue(String issueId);

    List<Issue> getIssues(IssueSearchRequestDto issueSearchRequestDto);

    String updateIssue(String issueId, IssueStatus status, String resolution);

    String resolveIssue(String issueId, String resolution);

    Map<String,List<String>> viewAgentsWorkHistory();
}
