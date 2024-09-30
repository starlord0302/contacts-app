package com.kare.contactsbackend.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BaseFileService {

  private final String uploadDirectory = new File("uploads").getAbsolutePath();

  public String uploadFile(MultipartFile file) {

    Path uploadPath = Paths.get(uploadDirectory);

    String fileName = file.getOriginalFilename();
    Path filePath = uploadPath.resolve(fileName);

    try {
      file.transferTo(filePath.toFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return this.getBaseUrl() + "/uploads/" + fileName;
  }

  private String getBaseUrl() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return request.getRequestURL().toString().replace(request.getRequestURI(), "");
  }
}
