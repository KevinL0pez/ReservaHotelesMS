package org.reservahoteles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.reservahoteles.enums.Role;

@Data
public class LoginRequestDto {
//    @JsonProperty("email") // Esto asignará el campo JSON "username" al campo Java "EmailUser"
    private String emailUser;
    private String password;
    private Role role;
}