package csis3275.project.seasell.user.dto;

import csis3275.project.seasell.user.model.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@Data
public class UserDto {
//    private int id;
    private String password;
    private String email;
    private String preferredUserName;
    private String address;
    private String mobile;
}
