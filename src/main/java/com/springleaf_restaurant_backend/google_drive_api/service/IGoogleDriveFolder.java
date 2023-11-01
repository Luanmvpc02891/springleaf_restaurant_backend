package com.springleaf_restaurant_backend.google_drive_api.service;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.springleaf_restaurant_backend.google_drive_api.model.GoogleDriveFoldersDTO;

public interface IGoogleDriveFolder {
    List<GoogleDriveFoldersDTO> getAllFolder() throws IOException, GeneralSecurityException;
    void createFolder(String folderName) throws Exception;
    void deleteFolder(String id) throws Exception;
}