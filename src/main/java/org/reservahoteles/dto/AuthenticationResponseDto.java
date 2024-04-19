package org.reservahoteles.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String token;
    private String message;
}
