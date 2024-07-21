package com.marcinsz.recruitingtask.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@Configuration
@ConfigurationProperties(prefix = "git.api")
public class ApiConfig {
    private String urlForGetUserRepos;
    private String urlForGetUserReposBranches;
    private String header;

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
