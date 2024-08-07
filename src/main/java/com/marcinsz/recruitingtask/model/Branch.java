package com.marcinsz.recruitingtask.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {
    private String name;
    private String lastCommitSha;
}
