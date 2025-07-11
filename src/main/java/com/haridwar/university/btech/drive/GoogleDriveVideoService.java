package com.haridwar.university.btech.drive;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveVideoService {
    @Autowired
    private GoogleDriveService googleDriveService;

    // Upload Video to Google Drive

    public String uploadVideo(MultipartFile file) throws IOException, GeneralSecurityException {
        Drive driveService = googleDriveService.getDriveService();

        // Convert MultipartFile to a temporary file
        java.io.File tempFile = java.io.File.createTempFile("upload_", file.getOriginalFilename());
        Files.copy(file.getInputStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setMimeType("video/mp4"); // Adjust according to your video format
//        String folderId = "1nubDo7ZimlYYcCKnLRxLGUTxlKpZwiIR";//thix is the old one okay
        String folderId = "1bq3KMOqF0q5_z24lP0BgOlAUrnupE0r-";

        fileMetadata.setParents(Collections.singletonList(folderId));

        FileContent mediaContent = new FileContent("video/mp4", tempFile);
        File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id, webViewLink, webContentLink")
                .execute();

        // Make the file publicly accessible
        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");
        driveService.permissions().create(uploadedFile.getId(), permission).execute();

        // Clean up temporary file
        tempFile.delete();

        return "Uploaded Video ID: " + uploadedFile.getId() +
                ", Link: " + uploadedFile.getWebViewLink();
    }

    // Get All Videos from Google Drive
    public List<String> getAllVideos() throws IOException, GeneralSecurityException {
        Drive driveService = googleDriveService.getDriveService();

        List<String> videoList = new ArrayList<>();
        FileList result = driveService.files().list()
                .setQ("mimeType contains 'video/'")
                .setFields("files(id, name, webViewLink)")
                .execute();

        for (File file : result.getFiles()) {
            videoList.add("ID: " + file.getId() + ", Name: " + file.getName() + ", Link: " + file.getWebViewLink());
        }
        return videoList;
    }

    // Get Video by ID
    public String getVideoById(String videoId) throws IOException, GeneralSecurityException {
        Drive driveService = googleDriveService.getDriveService();
        File file = driveService.files().get(videoId)
                .setFields("id, name, webViewLink, webContentLink")
                .execute();
        return "ID: " + file.getId() + ", Name: " + file.getName() + ", Link: " + file.getWebViewLink();
    }
}