package com.springleaf_restaurant_backend.user.restcontrollers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import net.minidev.json.JSONObject;

@RestController
public class ImageRestController {

  private final ServletContext servletContext;

  @Autowired
  public ImageRestController(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @PostMapping(value = "/public/create/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) {
    try {
      String uploadPath = servletContext.getRealPath("/static/uploads");
      File directory = new File(uploadPath);
      if (!directory.exists()) {
        directory.mkdirs();
      }
      Path filePath = Paths.get(uploadPath + File.separator + file.getOriginalFilename());
      Files.write(filePath, file.getBytes());
      Map<String, String> response = new HashMap<>();
      response.put("message", "Image uploaded successfully");
      response.put("imagePath", filePath.toString());
      JSONObject jsonResponse = new JSONObject(response);
      return ResponseEntity.ok(jsonResponse.toString());
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image!");
    }
  }
  @GetMapping(value = "/public/getImage/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
    String imagePath = servletContext.getRealPath("/static/uploads/" + fileName);
    Path path = Paths.get(imagePath);
    Resource resource = new UrlResource(path.toUri());

    if (resource.exists() || resource.isReadable()) {
      return ResponseEntity.ok().body(resource);
    } else {
      throw new RuntimeException("Could not read the file!");
    }
  }
}
