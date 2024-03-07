package org.reservahoteles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestDto {
//    @JsonProperty("email") // Esto asignará el campo JSON "username" al campo Java "EmailUser"
    private String emailUser;
    private String password;
}