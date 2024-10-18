package service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ObjectStorageService {
    String uploadFile(String bucketName, String directoryPath, MultipartFile file);
    void deleteFile(String bucketName, String directoryPath, String fileName);
    void deleteFiles(String bucketName, String directoryPath, List<String> fileNames);
}
