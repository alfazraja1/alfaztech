package com.alfaztech.authService.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alfaztech.authService.service.GitHubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	@Value("${github.token}")
    private String githubToken;

    @Value("${github.repository}")
    private String repository;
    @Autowired
    private GitHubService gitHubService;
    

    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
       /* try {
            // Convert the image to base64 (GitHub API requires base64 encoding for file uploads)
            String encodedImage = Base64.getEncoder().encodeToString(image.getBytes());

            // Construct the GitHub API URL
            String filePath = "uploads/" + image.getOriginalFilename();
            String apiUrl = "https://api.github.com/repos/" + repository + "/contents/" + filePath;

            // Prepare the request body
            String requestBody = "{\n" +
                    "  \"message\": \"Upload image " + image.getOriginalFilename() + "\",\n" +
                    "  \"content\": \"" + encodedImage + "\"\n" +
                    "}";

            // Set up headers (Authorization with token)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "token " + githubToken);
            headers.set("Content-Type", "application/json");

            // Create the request entity
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // Use RestTemplate to send the request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.PUT, entity, String.class);

            // If the upload is successful, return the GitHub URL of the uploaded image
            if (response.getStatusCode().is2xxSuccessful()) {
                String imageUrl = "https://raw.githubusercontent.com/" + repository + "/refs/heads/main/" + filePath;
                return ResponseEntity.ok("Image uploaded successfully: " + imageUrl);
            } else {
                return ResponseEntity.status(500).body("Error uploading image to GitHub.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading the image.");
        }*/
    	
    	 String filePath = "uploads/" + image.getOriginalFilename(); // Use the file name as the path in GitHub repo

        try {
            // Check if file exists in the GitHub repo
            boolean fileExists = gitHubService.checkIfFileExists(filePath);

            // Convert file content to Base64
            String base64Content = gitHubService.encodeFileToBase64(image.getBytes());

            if (fileExists) {
                // If file exists, update it
            	 String sha = getFileSha(filePath); // You should get the current sha of the file (this can be done in a previous step)
                //gitHubService.updateFile(filePath, base64Content, "Update file content", sha);
               String encodedImage = Base64.getEncoder().encodeToString(image.getBytes());

                // Construct the GitHub API URL
               // String filePath = "uploads/" + image.getOriginalFilename();
                String apiUrl = "https://api.github.com/repos/" + repository + "/contents/" + filePath;

                // Prepare the request body
                String requestBody = "{\n" +
                        "  \"message\": \"Replace file " + image.getOriginalFilename() + "\",\n" +
                        "  \"content\": \"" + encodedImage + "\",\n" +
                        "  \"sha\": \"" + sha + "\"\n" +
                        "}";

                // Set up headers (Authorization with token)
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "token " + githubToken);
                headers.set("Content-Type", "application/json");

                // Create the request entity
                HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

                // Use RestTemplate to send the request
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.PUT, entity, String.class);

                // If the upload is successful, return the GitHub URL of the uploaded image
                if (response.getStatusCode().is2xxSuccessful()) {
                    String imageUrl = "https://raw.githubusercontent.com/" + repository + "/refs/heads/main/" + filePath;
                    return ResponseEntity.ok(imageUrl);
                } else {
                    return ResponseEntity.status(500).body("Error uploading image to GitHub.");
                }
            } else {
                // If file does not exist, create it
                //gitHubService.createFile(filePath, base64Content, "Add new file");
            	 String encodedImage = Base64.getEncoder().encodeToString(image.getBytes());

                 // Construct the GitHub API URL
                // String filePath = "uploads/" + image.getOriginalFilename();
                 String apiUrl = "https://api.github.com/repos/" + repository + "/contents/" + filePath;

                 // Prepare the request body
                 String requestBody = "{\n" +
                         "  \"message\": \"Upload image " + image.getOriginalFilename() + "\",\n" +
                         "  \"content\": \"" + encodedImage + "\"\n" +
                         "}";

                 // Set up headers (Authorization with token)
                 HttpHeaders headers = new HttpHeaders();
                 headers.set("Authorization", "token " + githubToken);
                 headers.set("Content-Type", "application/json");

                 // Create the request entity
                 HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

                 // Use RestTemplate to send the request
                 RestTemplate restTemplate = new RestTemplate();
                 ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.PUT, entity, String.class);

                 // If the upload is successful, return the GitHub URL of the uploaded image
                 if (response.getStatusCode().is2xxSuccessful()) {
                     String imageUrl = "https://raw.githubusercontent.com/" + repository + "/refs/heads/main/" + filePath;
                     return ResponseEntity.ok(imageUrl);
                 } else {
                     return ResponseEntity.status(500).body("Error uploading image to GitHub.");
                 }
             }  

           // return ResponseEntity.ok("File processed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing the file: " + e.getMessage());
        }
    
    }
    
    private String getFileSha(String filePath) {
        String url = "https://api.github.com/repos/" + repository + "/contents/" + filePath;
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Fetch the file metadata to get the sha
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            String responseBody = response.getBody();

            // Parse the response to extract the sha (you may need to use a JSON library like Jackson)
            // Assuming the response contains a "sha" field, parse it (this example uses simple String parsing)
            String sha = responseBody.split("\"sha\":\"")[1].split("\"")[0];
            return sha;
        } catch (Exception e) {
            throw new RuntimeException("Error getting SHA of file: " + filePath);
        }
    }
}