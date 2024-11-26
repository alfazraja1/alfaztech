package com.alfaztech.authService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Base64;

@Service
public class GitHubService {

	@Value("${github.token}")
    private String githubToken;

    @Value("${github.repository}")
    private String repository;
    

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Check if the file exists in the repo
    public boolean checkIfFileExists(String filePath) {
        String url = "https://api.github.com/repos/" + repository + "/contents/" + filePath;
        try {
            restTemplate.getForObject(url, String.class);  // Try fetching the file
            return true; // File exists
        } catch (HttpClientErrorException.NotFound e) {
            return false; // File does not exist
        }
    }

    // Update the file in the repository
    public void updateFile(String filePath, String base64Content, String commitMessage, String sha) {
        String url = "https://api.github.com/repos/" + repository + "/contents/" + filePath;

        String jsonBody = String.format("{\"message\": \"%s\", \"content\": \"%s\", \"sha\": \"%s\"}",
                commitMessage, base64Content, sha);

        restTemplate.put(url, jsonBody);
    }

    // Create a new file in the repository
    public void createFile(String filePath, String base64Content, String commitMessage) {
        String url = "https://api.github.com/repos/" + repository + "/contents/" + filePath;

        String jsonBody = String.format("{\"message\": \"%s\", \"content\": \"%s\"}", commitMessage, base64Content);

        restTemplate.put(url, jsonBody);
    }

    // Encode file content to Base64
    public String encodeFileToBase64(byte[] fileContent) {
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
