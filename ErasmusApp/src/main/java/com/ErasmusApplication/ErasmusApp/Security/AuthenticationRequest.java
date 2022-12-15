package com.ErasmusApplication.ErasmusApp.Security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {
    private String userName;
    private String password;
}
