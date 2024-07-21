package com.marcinsz.recruitingtask.mapper;

import com.marcinsz.recruitingtask.api.UserBranchFromApi;
import com.marcinsz.recruitingtask.model.Branch;
import lombok.Data;

import java.util.List;

@Data
public class ApiMapper {

    public static List<Branch> mapListOfUserBranchesFromApiToListOfBranch(List<UserBranchFromApi> userBranchFromApi) {
        return userBranchFromApi.stream()
                .map(userBranchFromApi1 ->
                        Branch.builder()
                        .name(userBranchFromApi1.getName())
                        .lastCommitSha(userBranchFromApi1.getCommit().getSha())
                        .build())
                .toList();
    }
}
