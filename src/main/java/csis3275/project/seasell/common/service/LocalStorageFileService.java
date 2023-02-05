package csis3275.project.seasell.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @Value("${storage.local.basePath:.}")
    @Setter
    private String basePath = ".";

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
    public byte[] retrieve(String key) throws IOException {
        File file = new File(basePath + "/" + key);
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        } catch (IOException e) {
            log.error("Unable to read file locally", e);
            throw e;
        }
    }
}
