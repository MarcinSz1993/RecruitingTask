package com.marcinsz.recruitingtask.service;

import com.marcinsz.recruitingtask.api.UserBranchFromApi;
import com.marcinsz.recruitingtask.api.UserRepoFromApi;
import com.marcinsz.recruitingtask.configuration.ApiConfig;
import com.marcinsz.recruitingtask.exception.UserDoesNotExistException;
import com.marcinsz.recruitingtask.mapper.ApiMapper;
import com.marcinsz.recruitingtask.model.Branch;
import com.marcinsz.recruitingtask.model.RequiredResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final WebClient webClient;
    private final ApiConfig apiConfig;

    public List<RequiredResponse> requiredResponse(String username) {
        List<UserRepoFromApi> userRepos = getUserRepos(username);
        List<UserRepoFromApi> nonForkedRepos = userRepos.stream()
                .filter(repo -> !repo.isFork())
                .toList();
        return nonForkedRepos.stream()
                .map(repo -> {
                    List<UserBranchFromApi> userBranches = getUserRepoBranches(username, repo.getName());
                    List<Branch> branches = ApiMapper.mapListOfUserBranchesFromApiToListOfBranch(userBranches);
                    return new RequiredResponse(repo.getName(), repo.getOwner().getLogin(), branches);
                })
                .collect(Collectors.toList());
    }

    public List<UserRepoFromApi> getUserRepos(String username) {
        try {
            String url = apiConfig.getUrlForGetUserRepos();
            String fullUrl = UriComponentsBuilder.fromUriString(url)
                    .buildAndExpand(username)
                    .toUriString();
            return webClient.get()
                    .uri(fullUrl)
                    .header(HttpHeaders.ACCEPT, apiConfig.getHeader())
                    .retrieve()
                    .bodyToFlux(UserRepoFromApi.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException exception) {
            throw new UserDoesNotExistException(username);
        }

    }

    public List<UserBranchFromApi> getUserRepoBranches(String username, String repo) {
        String url = apiConfig.getUrlForGetUserReposBranches();
        String fullUrl = UriComponentsBuilder.fromUriString(url)
                .buildAndExpand(username,repo)
                .toUriString();
        return webClient.get()
                .uri(fullUrl)
                .header(HttpHeaders.ACCEPT, apiConfig.getHeader())
                .retrieve()
                .bodyToFlux(UserBranchFromApi.class)
                .collectList()
                .block();
    }
}
