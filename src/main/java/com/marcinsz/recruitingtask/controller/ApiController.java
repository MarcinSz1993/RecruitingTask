package com.marcinsz.recruitingtask.controller;

import com.marcinsz.recruitingtask.model.RequiredResponse;
import com.marcinsz.recruitingtask.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/result")
    public List<RequiredResponse> requiredResponses(@RequestParam String username) {
        return apiService.requiredResponse(username);
    }

}
