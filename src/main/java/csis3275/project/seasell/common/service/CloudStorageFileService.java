package csis3275.project.seasell.common.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import csis3275.project.seasell.common.dto.FileWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
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
    public FileWrapper retrieve(String key) throws IOException {
        try {
            S3Object object = s3Client.getObject(bucket, key);
            Date lastModified = object.getObjectMetadata().getLastModified();
            return new FileWrapper(lastModified, object.getObjectContent().readAllBytes());
        } catch (SdkClientException e) {
            log.error("Error reading file from S3", e);
            throw new IOException(e);
        }
    }
}
