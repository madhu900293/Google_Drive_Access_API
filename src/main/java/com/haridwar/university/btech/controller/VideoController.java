package com.haridwar.university.btech.controller;

import com.haridwar.university.btech.drive.GoogleDriveVideoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private GoogleDriveVideoService videoService;

    // Upload Video API
    @Operation(summary = "Upload a video to Google Drive")
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadVideo(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        try {
            String result = videoService.uploadVideo(file);
            response.put("response", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException | GeneralSecurityException e) {
            response.put("response", "Error uploading video");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get All Videos API
    @Operation(summary = "Get a list of all videos from Google Drive")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllVideos() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<String> videos = videoService.getAllVideos();
            response.put("response", "Videos retrieved successfully");
            response.put("videos", videos);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException | GeneralSecurityException e) {
            response.put("response", "Error retrieving videos");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get Video by ID API
    @Operation(summary = "Get a specific video by ID from Google Drive")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getVideoById(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            String video = videoService.getVideoById(id);
            response.put("response", "Video retrieved successfully");
            response.put("video", video);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException | GeneralSecurityException e) {
            response.put("response", "Error retrieving video");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
