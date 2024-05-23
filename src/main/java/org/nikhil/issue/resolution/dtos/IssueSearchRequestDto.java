package org.nikhil.issue.resolution.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nikhil.issue.resolution.constants.IssueType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueSearchRequestDto {
    private String email;
    private IssueType issueType;
}
