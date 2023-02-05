package csis3275.project.seasell.common;

import csis3275.project.seasell.common.service.FileService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    FileService fileService;

    @GetMapping(value = "/{fileName}", produces = { MediaType.IMAGE_JPEG_VALUE })
    public byte[] getProductImage(@PathVariable String fileName) throws IOException {
        return fileService.retrieve(fileName);
    }
}
