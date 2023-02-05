package csis3275.project.seasell.common.service;

import static org.junit.jupiter.api.Assertions.*;

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
        byte[] fileContent = fileService.retrieve(fileName);
        assertEquals("ABC", new String(fileContent));
        // Clean up
        new File("./images/" + fileName).delete();
    }
}