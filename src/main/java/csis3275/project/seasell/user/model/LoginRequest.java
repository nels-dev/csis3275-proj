package csis3275.project.seasell.user.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
