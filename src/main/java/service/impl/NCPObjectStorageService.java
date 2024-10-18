package service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.ObjectStorageService;
import spring.config.NaverConfiguration;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class NCPObjectStorageService implements ObjectStorageService {
    final AmazonS3 s3;

    @Autowired
    public NCPObjectStorageService(NaverConfiguration naverConfiguration) {
        this.s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        naverConfiguration.getEndPoint(), naverConfiguration.getRegionName()))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(naverConfiguration.getAccessKey(), naverConfiguration.getSecretKey())))
                .build();
    }

    @Override
    public String uploadFile(String bucketName, String directoryPath, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());

            s3.putObject(new PutObjectRequest(bucketName, directoryPath + fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return "https://kr.object.ncloudstorage.com/" + bucketName + "/" + directoryPath + fileName;
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    @Override
    public void deleteFile(String bucketName, String directoryPath, String fileName) {
        s3.deleteObject(bucketName, directoryPath + fileName);
    }

    @Override
    public void deleteFiles(String bucketName, String directoryPath, List<String> fileNames) {
        for (String fileName : fileNames) {
            s3.deleteObject(bucketName, directoryPath + fileName);
        }
    }
}
