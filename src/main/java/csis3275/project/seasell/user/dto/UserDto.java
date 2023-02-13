package csis3275.project.seasell.user.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserDto {
    // private int id;
    private String password;
    private String email;
    private String preferredUserName;
    private String address;
    private String mobile;

}
