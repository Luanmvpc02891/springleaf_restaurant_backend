package com.springleaf_restaurant_backend.google_drive_api.service;

import org.springframework.web.multipart.MultipartFile;

import com.springleaf_restaurant_backend.google_drive_api.model.GoogleDriveFileDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IGoogleDriveFile {
    List<GoogleDriveFileDTO> getAllFile() throws IOException, GeneralSecurityException;
    void deleteFile(String id) throws Exception;
    void uploadFile(MultipartFile file, String filePath, boolean isPublic) throws Exception;
    void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException;
}