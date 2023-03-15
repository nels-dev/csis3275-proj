package csis3275.project.seasell.common.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileWrapper {

    private Date lastModified;
    private byte[] content;
}
