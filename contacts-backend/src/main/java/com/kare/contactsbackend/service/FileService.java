package com.kare.contactsbackend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

  private final AmazonS3 amazonS3;

  @Value("${contacts.s3.url}")
  private String endpointUrl;
  @Value("${contacts.s3.bucketName}")
  private String bucketName;

  public FileService(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  public String uploadFile(MultipartFile multipartFile) {
    String fileURL = "";
    File file = null;
    try {
      file = convertMultipartFileToFile(multipartFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String fileName = multipartFile.getOriginalFilename();
    fileURL = endpointUrl + "/" + bucketName + "/" + fileName;
    uploadFileToBucket(fileName, file);
    file.delete();
    return fileURL;
  }

  private File convertMultipartFileToFile(MultipartFile file) throws IOException {
    File convertedFile = new File(file.getOriginalFilename());
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    }
    return convertedFile;
  }

  private void uploadFileToBucket(String fileName, File file) {
    amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
            .withCannedAcl(CannedAccessControlList.PublicRead));
  }
}