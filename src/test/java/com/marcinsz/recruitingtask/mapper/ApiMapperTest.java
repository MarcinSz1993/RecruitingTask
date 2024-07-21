package com.marcinsz.recruitingtask.mapper;

import com.marcinsz.recruitingtask.api.Commit;
import com.marcinsz.recruitingtask.api.UserBranchFromApi;
import com.marcinsz.recruitingtask.model.Branch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiMapperTest {

    @Test
    public void shouldCorrectlyMapListOfUserBranchesFromApiToListOfBranch() {
        UserBranchFromApi userBranch1 = UserBranchFromApi
                .builder()
                .name("exampleName1")
                .commit(new Commit("111111111", "https://exampleurl1.pl"))
                .build();

        UserBranchFromApi userBranch2 = UserBranchFromApi
                .builder()
                .name("exampleName2")
                .commit(new Commit("2222222222", "https://exampleurl2.pl"))
                .build();
        List<UserBranchFromApi> userBranchFromApi = new ArrayList<>();
        userBranchFromApi.add(userBranch1);
        userBranchFromApi.add(userBranch2);

        Branch expectedBranch1 = Branch.builder()
                .name(userBranch1.getName())
                .lastCommitSha(userBranch1.getCommit().getSha())
                .build();

        Branch expectedBranch2 = Branch.builder()
                .name(userBranch2.getName())
                .lastCommitSha(userBranch2.getCommit().getSha())
                .build();

        List<Branch> expectedListOfBranches = new ArrayList<>();
        expectedListOfBranches.add(expectedBranch1);
        expectedListOfBranches.add(expectedBranch2);

        List<Branch> actualListOfBranch = ApiMapper.mapListOfUserBranchesFromApiToListOfBranch(userBranchFromApi);

        assertEquals(expectedListOfBranches.size(), actualListOfBranch.size());
        assertEquals(expectedListOfBranches.getFirst().getName(), actualListOfBranch.getFirst().getName());
        assertEquals(expectedListOfBranches.getFirst().getLastCommitSha(), actualListOfBranch.getFirst().getLastCommitSha());
        assertEquals(expectedListOfBranches.getLast().getName(), actualListOfBranch.getLast().getName());
        assertEquals(expectedListOfBranches.getLast().getLastCommitSha(), actualListOfBranch.getLast().getLastCommitSha());
    }

    @Test
    public void shouldReturnEmptyListWhenInputIsEmpty() {
        List<Branch> actualResult = ApiMapper.mapListOfUserBranchesFromApiToListOfBranch(Collections.emptyList());
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void shouldCorrectlyMapListOfUserBranchesToListOfBranchWhenInputIsSingleElementList() {
        UserBranchFromApi userBranch1 = UserBranchFromApi
                .builder()
                .name("exampleName1")
                .commit(new Commit("111111111", "https://exampleurl1.pl"))
                .build();

        List<UserBranchFromApi> singleListOfUserBranchFromApi = List.of(userBranch1);

        Branch expectedBranch = Branch.builder()
                .name(userBranch1.getName())
                .lastCommitSha(userBranch1.getCommit().getSha())
                .build();

        List<Branch> expectedListOfBranches = List.of(expectedBranch);
        List<Branch> actualListOfBranch = ApiMapper.mapListOfUserBranchesFromApiToListOfBranch(singleListOfUserBranchFromApi);

        assertEquals(expectedListOfBranches.size(), actualListOfBranch.size());
        assertEquals(expectedListOfBranches.getFirst().getName(), actualListOfBranch.getFirst().getName());
    }

}