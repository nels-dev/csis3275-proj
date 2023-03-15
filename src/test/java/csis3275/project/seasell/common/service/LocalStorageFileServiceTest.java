package csis3275.project.seasell.common.service;

import static org.junit.jupiter.api.Assertions.*;

import csis3275.project.seasell.common.dto.FileWrapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class LocalStorageFileServiceTest {

    @Test
    public void storeAndRetrieveFile_success() throws IOException {
        LocalStorageFileService fileService = new LocalStorageFileService();
        fileService.setBasePath("./images");
        String fileName = fileService.store("ABC".getBytes(StandardCharsets.UTF_8), "dat");
        FileWrapper fileWrapper = fileService.retrieve(fileName);
        assertEquals("ABC", new String(fileWrapper.getContent()));
        // Clean up
        new File("./images/" + fileName).delete();
    }
}