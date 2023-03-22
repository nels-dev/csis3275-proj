package csis3275.project.seasell.common.service;

import csis3275.project.seasell.common.dto.FileWrapper;
import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "storage.type", matchIfMissing = true, havingValue = "local")
@Slf4j
public class LocalStorageFileService implements FileService {

    @Value("${storage.local.base-path}")
    @Setter
    private String basePath;

    @Override
    public String store(byte[] fileContent, String extension) throws IOException {
        String fileName = UUID.randomUUID() + "." + extension;
        File file = new File(basePath + "/" + fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent);
        } catch (IOException e) {
            log.error("Unable to save file locally", e);
            throw e;
        }
        return fileName;
    }

    @Override
    public FileWrapper retrieve(String key) {
        File file = new File(basePath + "/" + key);
        try (FileInputStream fis = new FileInputStream(file)) {
            return new FileWrapper(new Date(file.lastModified()), fis.readAllBytes());
        } catch (IOException e) {
            log.error("Unable to read file locally", e);
            throw new ResourceNotFoundException();
        }
    }
}
