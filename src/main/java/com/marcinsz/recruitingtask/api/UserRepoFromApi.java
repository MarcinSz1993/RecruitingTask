package com.marcinsz.recruitingtask.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserRepoFromApi {
    private long id;
    @JsonProperty("node_id")
    private String nodeID;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private Owner owner;
    @JsonProperty("html_url")
    private String htmlURL;
    private String description;
    private boolean fork;
    private String url;
    @JsonProperty("forks_url")
    private String forksURL;
    @JsonProperty("keys_url")
    private String keysURL;
    @JsonProperty("collaborators_url")
    private String collaboratorsURL;
    @JsonProperty("teams_url")
    private String teamsURL;
    @JsonProperty("hooks_url")
    private String hooksURL;
    @JsonProperty("issue_events_url")
    private String issueEventsURL;
    @JsonProperty("events_url")
    private String eventsURL;
    @JsonProperty("assignees_url")
    private String assigneesURL;
    @JsonProperty("branches_url")
    private String branchesURL;
    @JsonProperty("tags_url")
    private String tagsURL;
    @JsonProperty("blobs_url")
    private String blobsURL;
    @JsonProperty("git_tags_url")
    private String gitTagsURL;
    @JsonProperty("git_refs_url")
    private String gitRefsURL;
    @JsonProperty("trees_url")
    private String treesURL;
    @JsonProperty("statuses_url")
    private String statusesURL;
    @JsonProperty("languages_url")
    private String languagesURL;
    @JsonProperty("stargazers_url")
    private String stargazersURL;
    @JsonProperty("contributors_url")
    private String contributorsURL;
    @JsonProperty("subscribers_url")
    private String subscribersURL;
    @JsonProperty("subscription_url")
    private String subscriptionURL;
    @JsonProperty("commits_url")
    private String commitsURL;
    @JsonProperty("git_commits_url")
    private String gitCommitsURL;
    @JsonProperty("comments_url")
    private String commentsURL;
    @JsonProperty("issue_comment_url")
    private String issueCommentURL;
    @JsonProperty("contents_url")
    private String contentsURL;
    @JsonProperty("compare_url")
    private String compareURL;
    @JsonProperty("merge_url")
    private String mergesURL;
    @JsonProperty("archive_url")
    private String archiveURL;
    @JsonProperty("downloads_url")
    private String downloadsURL;
    @JsonProperty("issues_url")
    private String issuesURL;
    @JsonProperty("pulls_url")
    private String pullsURL;
    @JsonProperty("milestones_url")
    private String milestonesURL;
    @JsonProperty("notifications_url")
    private String notificationsURL;
    @JsonProperty("labels_url")
    private String labelsURL;
    @JsonProperty("releases_url")
    private String releasesURL;
    @JsonProperty("deployments_url")
    private String deploymentsURL;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
    @JsonProperty("pushed_at")
    private OffsetDateTime pushedAt;
    @JsonProperty("git_url")
    private String gitURL;
    @JsonProperty("ssh_url")
    private String sshURL;
    @JsonProperty("clone_url")
    private String cloneURL;
    @JsonProperty("svn_url")
    private String svnURL;
    private String homepage;
    private long size;
    @JsonProperty("stargazers_count")
    private long stargazersCount;
    @JsonProperty("watcher_count")
    private long watchersCount;
    private String language;
    @JsonProperty("has_issues")
    private boolean hasIssues;
    @JsonProperty("has_projects")
    private boolean hasProjects;
    @JsonProperty("has_downloads")
    private boolean hasDownloads;
    @JsonProperty("has_wiki")
    private boolean hasWiki;
    @JsonProperty("has_pages")
    private boolean hasPages;
    @JsonProperty("has_discussions")
    private boolean hasDiscussions;
    @JsonProperty("forks_count")
    private long forksCount;
    @JsonProperty("mirror_url")
    private Object mirrorURL;
    private boolean archived;
    private boolean disabled;
    @JsonProperty("open_issues_count")
    private long openIssuesCount;
    private Object license;
    @JsonProperty("allow_forking")
    private boolean allowForking;
    @JsonProperty("is_template")
    private boolean isTemplate;
    @JsonProperty("web_commit_signoff_required")
    private boolean webCommitSignoffRequired;
    private String[] topics;
    private String visibility;
    private long forks;
    @JsonProperty("open_issues")
    private long openIssues;
    private long watchers;
    @JsonProperty("default_branch")
    private String defaultBranch;
}
