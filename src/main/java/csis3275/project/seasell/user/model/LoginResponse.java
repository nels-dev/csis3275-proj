package csis3275.project.seasell.user.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LoginResponse {
    String token;
    AppUser user;
}
