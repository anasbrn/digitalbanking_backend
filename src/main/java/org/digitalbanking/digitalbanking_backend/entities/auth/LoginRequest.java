package org.digitalbanking.digitalbanking_backend.entities.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
