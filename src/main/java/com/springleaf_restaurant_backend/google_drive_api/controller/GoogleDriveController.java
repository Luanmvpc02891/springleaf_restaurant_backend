package com.springleaf_restaurant_backend.google_drive_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springleaf_restaurant_backend.google_drive_api.config.GoogleDriveConfig;
import com.springleaf_restaurant_backend.google_drive_api.model.GoogleDriveFileDTO;
import com.springleaf_restaurant_backend.google_drive_api.model.GoogleDriveFoldersDTO;
import com.springleaf_restaurant_backend.google_drive_api.service.impl.GoogleDriveFileService;
import com.springleaf_restaurant_backend.google_drive_api.service.impl.GoogleDriveFolderService;
import com.springleaf_restaurant_backend.google_drive_api.service.impl.GoogleFileManager;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController(value = "googleDriveController")
public class GoogleDriveController {

    @Autowired
    GoogleDriveFileService googleDriveFileService;

    @Autowired
    GoogleDriveFolderService googleDriveFolderService;

    @Autowired
    GoogleFileManager googleFileManager;

    @Autowired
    GoogleDriveConfig googleDriveConfig;

    // Get all file on drive
    @GetMapping("/api/")
    public ModelAndView pageIndex() throws IOException, GeneralSecurityException {
        ModelAndView mav = new ModelAndView("index");

        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();
        List<GoogleDriveFoldersDTO> listFolder = googleDriveFolderService.getAllFolder();
        mav.addObject("DTO_FOLDER", listFolder);
        mav.addObject("DTO_FILE", listFile);
        return mav;
    }

    // Upload file to public
    @PostMapping(value = "/api/upload/file")
    public ResponseEntity<String> uploadFile(
            @RequestParam("fileUpload") MultipartFile fileUpload, // file upload
            @RequestParam("filePath") String pathFile, // thư mục
            @RequestParam("shared") String shared) { // chế độ công khai
        try {
            System.out.println(fileUpload.getContentType());
            if(!fileUpload.getContentType().equals("image/jpge")){
                return new ResponseEntity<>("File has an incorrect format", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (pathFile.equals("")){
                pathFile = "Root"; // Save to default folder if the user does not select a
            }
            List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();
            for (GoogleDriveFileDTO googleDriveFileDTO : listFile) {
                if(googleDriveFileDTO.getName().equals(fileUpload.getOriginalFilename())){
                    return new ResponseEntity<>("Image  already exists", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            googleDriveFileService.uploadFile(fileUpload, pathFile, Boolean.parseBoolean(shared));
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete file by id
    @GetMapping("/api/delete/file/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable String id) throws Exception {
        googleDriveFileService.deleteFile(id);
        return new ResponseEntity<>("Delete file is success", HttpStatus.OK);
    }

    // Download file
    @GetMapping("/api/download/file/{id}")
    public void downloadFile(@PathVariable String id, HttpServletResponse response)
            throws IOException, GeneralSecurityException {
        googleDriveFileService.downloadFile(id, response.getOutputStream());
    }

    // Get all root folder on drive
    @GetMapping("/api/list/folders") 
    public ModelAndView listFolder() throws IOException, GeneralSecurityException {
        ModelAndView mav = new ModelAndView("list_folder");
        List<GoogleDriveFoldersDTO> listFolder = googleDriveFolderService.getAllFolder();
        mav.addObject("DTO", listFolder);
        return mav;
    }

    // Create folder
    @PostMapping("/api/create/folder")
    public ModelAndView createFolder(@RequestParam("folderName") String folderName) throws Exception {
        googleDriveFolderService.createFolder(folderName);
        return new ModelAndView("redirect:" + "/list/folders");
    }

    // Delete folder by id
    @GetMapping("/api/delete/folder/{id}")
    public ModelAndView deleteFolder(@PathVariable String id) throws Exception {
        googleDriveFolderService.deleteFolder(id);
        return new ModelAndView("redirect:" + "/list/folders");
    }
}