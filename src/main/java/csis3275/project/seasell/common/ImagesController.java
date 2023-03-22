package csis3275.project.seasell.common;

import csis3275.project.seasell.common.dto.FileWrapper;
import csis3275.project.seasell.common.service.FileService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<byte[]> getProductImage(@PathVariable String fileName) {
        FileWrapper file = fileService.retrieve(fileName);
        return ResponseEntity.ok().lastModified(file.getLastModified().getTime())
                .cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)).body(file.getContent());
    }
}
