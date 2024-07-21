package com.marcinsz.recruitingtask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branches;
}
