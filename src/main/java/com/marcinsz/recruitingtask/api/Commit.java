package com.marcinsz.recruitingtask.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Commit {
    private String sha;
    private String url;
}
