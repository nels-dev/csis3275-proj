package csis3275.project.seasell.user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
