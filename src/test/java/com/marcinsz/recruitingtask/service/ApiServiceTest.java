package com.marcinsz.recruitingtask.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.marcinsz.recruitingtask.api.Commit;
import com.marcinsz.recruitingtask.api.Owner;
import com.marcinsz.recruitingtask.api.UserBranchFromApi;
import com.marcinsz.recruitingtask.api.UserRepoFromApi;
import com.marcinsz.recruitingtask.exception.UserDoesNotExistException;
import com.marcinsz.recruitingtask.model.Branch;
import com.marcinsz.recruitingtask.model.RequiredResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WireMockTest(httpPort = 8181, httpsEnabled = true)
@ExtendWith(WireMockExtension.class)
public class ApiServiceTest {

    @Autowired
    private ApiService apiService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSuccessfullyGetRequiredResponse() throws JsonProcessingException {
        Owner owner = getOwner();
        List<UserRepoFromApi> userRepoList = createUserRepos(owner);

        String userRepoJsonResponse = objectMapper.writeValueAsString(userRepoList);

        stubFor(get(urlEqualTo("/users/MarcinSz1993/repos"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(userRepoJsonResponse)));

        List<UserBranchFromApi> userBranchesList = createUserBranches();


        String userBranchesJsonResponse = objectMapper.writeValueAsString(userBranchesList);

        stubFor(get(urlEqualTo("/repos/MarcinSz1993/Repo1/branches"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(userBranchesJsonResponse)));

        List<Branch> expectedBranches = List.of(
                Branch.builder()
                        .name("Branch1")
                        .lastCommitSha("1111111111")
                        .build(),
                Branch.builder()
                        .name("Branch2")
                        .lastCommitSha("2222222222")
                        .build()
        );

        RequiredResponse expectedResponse = RequiredResponse.builder()
                .repositoryName("Repo1")
                .ownerLogin("MarcinSz1993")
                .branches(expectedBranches)
                .build();

        List<RequiredResponse> expectedResponses = List.of(expectedResponse);
        List<RequiredResponse> actualResponses = apiService.requiredResponse("MarcinSz1993");

        assertThat(actualResponses).isNotNull();
        assertThat(actualResponses).isEqualTo(expectedResponses);
    }


    @Test
    public void shouldSuccessfullyGetUserRepos() throws Exception {
        Owner owner = getOwner();
        List<UserRepoFromApi> userRepoList = createUserRepos(owner);

        String jsonResponse = objectMapper.writeValueAsString(userRepoList);

        stubFor(get(urlEqualTo("/users/MarcinSz1993/repos"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonResponse)));

        List<UserRepoFromApi> actualResponse = apiService.getUserRepos("MarcinSz1993");

        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.size()).isEqualTo(2);


        assertThat(actualResponse.getFirst().getId()).isEqualTo(1);
        assertThat(actualResponse.getFirst().getNodeID()).isEqualTo("1");
        assertThat(actualResponse.get(0).getName()).isEqualTo("Repo1");
        assertThat(actualResponse.get(0).getFullName()).isEqualTo("FullTestName1");

        assertThat(actualResponse.get(1).getId()).isEqualTo(2);
        assertThat(actualResponse.get(1).getNodeID()).isEqualTo("2");
        assertThat(actualResponse.get(1).getName()).isEqualTo("Repo2");
        assertThat(actualResponse.get(1).getFullName()).isEqualTo("FullTestName2");
    }

    @Test
    public void shouldThrowUserDoesNotExistExceptionWhenUserDoesNotExist() {
        stubFor(get(urlEqualTo("/users/NotExistingUser/repos"))
                .willReturn(notFound()
                        .withHeader("Content-Type", "application/json")));

        UserDoesNotExistException notExistingUser = assertThrows(UserDoesNotExistException.class, () -> apiService.getUserRepos("NotExistingUser"));
        assertEquals("User NotExistingUser does not exist.", notExistingUser.getMessage());
    }

    @Test
    public void shouldSuccessfullyGetUserRepoBranches() throws JsonProcessingException {
        List<UserBranchFromApi> expectedBranches = createUserBranches();

        String jsonResponse = objectMapper.writeValueAsString(expectedBranches);

        stubFor(get(urlEqualTo("/repos/MarcinSz1993/MyRepo/branches"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonResponse)));

        List<UserBranchFromApi> actualBranches = apiService.getUserRepoBranches("MarcinSz1993", "MyRepo");
        assertThat(actualBranches).isNotNull();
        assertThat(actualBranches.size()).isEqualTo(2);

        assertThat(actualBranches.getFirst().getName()).isEqualTo("Branch1");
        assertThat(actualBranches.getFirst().getCommit().getSha()).isEqualTo("1111111111");
        assertThat(actualBranches.getFirst().getCommit().getUrl()).isEqualTo("https://exampleurl1.com");
        assertTrue(actualBranches.getFirst().isProtected());

        assertThat(actualBranches.get(1).getName()).isEqualTo("Branch2");
        assertThat(actualBranches.get(1).getCommit().getSha()).isEqualTo("2222222222");
        assertThat(actualBranches.get(1).getCommit().getUrl()).isEqualTo("https://exampleurl2.com");
        assertFalse(actualBranches.get(1).isProtected());

    }

    private static Owner getOwner() {
        return Owner.builder()
                .login("MarcinSz1993")
                .build();
    }

    private List<UserRepoFromApi> createUserRepos(Owner owner) {
        UserRepoFromApi repo1 = UserRepoFromApi.builder()
                .id(1)
                .nodeID("1")
                .name("Repo1")
                .fullName("FullTestName1")
                .owner(owner)
                .fork(false)
                .build();

        UserRepoFromApi repo2 = UserRepoFromApi.builder()
                .id(2)
                .nodeID("2")
                .name("Repo2")
                .fullName("FullTestName2")
                .owner(owner)
                .fork(true)
                .build();

        return List.of(repo1, repo2);
    }

    private List<UserBranchFromApi> createUserBranches() {
        UserBranchFromApi branch1 = UserBranchFromApi.builder()
                .name("Branch1")
                .commit(new Commit("1111111111", "https://exampleurl1.com"))
                .isProtected(true)
                .build();

        UserBranchFromApi branch2 = UserBranchFromApi.builder()
                .name("Branch2")
                .commit(new Commit("2222222222", "https://exampleurl2.com"))
                .isProtected(false)
                .build();
        return List.of(branch1, branch2);
    }
}


