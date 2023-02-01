package csis3275.project.seasell.user.dto;

import csis3275.project.seasell.user.model.AppUser;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LoginResponse {
    String token;
    AppUser user;
}
