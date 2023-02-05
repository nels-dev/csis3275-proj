package csis3275.project.seasell.common.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "storage.type", matchIfMissing = true, havingValue = "cloud")
@Slf4j
public class CloudStorageFileService implements FileService {

    @Value("${storage.cloud.bucket}")
    private String bucket;

    @Autowired
    AmazonS3 s3Client;

    @Override
    public String store(byte[] fileContent, String extension) throws IOException {
        String fileName = UUID.randomUUID() + "." + extension;
        try {
            s3Client.putObject(bucket, fileName, new ByteArrayInputStream(fileContent), new ObjectMetadata());
        } catch (SdkClientException e) {
            log.error("Error uploading file to S3", e);
            throw new IOException(e);
        }

        return fileName;
    }

    @Override
    public byte[] retrieve(String key) throws IOException {
        try {
            S3Object object = s3Client.getObject(bucket, key);
            return object.getObjectContent().readAllBytes();
        } catch (SdkClientException e) {
            log.error("Error reading file from S3", e);
            throw new IOException(e);
        }
    }
}
