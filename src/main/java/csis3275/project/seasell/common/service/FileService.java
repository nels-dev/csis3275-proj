package csis3275.project.seasell.common.service;

import java.io.IOException;

public interface FileService {

    String store(byte[] fileContent, String extension) throws IOException;

    byte[] retrieve(String key) throws IOException;
    
    void update(String key,byte[] newContent) throws IOException;
}
