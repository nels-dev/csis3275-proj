package csis3275.project.seasell.common.service;

import csis3275.project.seasell.common.dto.FileWrapper;
import java.io.IOException;

public interface FileService {

    String store(byte[] fileContent, String extension) throws IOException;

    FileWrapper retrieve(String key) throws IOException;
}
