package com.marcinsz.recruitingtask.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBranchFromApi {
    private String name;
    private Commit commit;
    @JsonProperty("protected")
    private boolean isProtected;
}
