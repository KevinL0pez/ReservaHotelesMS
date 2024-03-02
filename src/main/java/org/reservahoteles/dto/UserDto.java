package org.reservahoteles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserDto {
    private Long idUser;

    @NotBlank(message = "Number Document is mandatory")
    @NotNull
    private String numberDocumentUser;

    @NotBlank(message = "Email is mandatory")
    @NotNull
    private String emailUser;

    @NotBlank(message = "Password is mandatory")
    @NotNull
    private String passwordUser;

    @NotBlank(message = "Names are mandatory")
    @NotNull
    private String namesUser;

    @NotBlank(message = "Last Names are mandatory")
    @NotNull
    private String lastNamesUser;

    @NotBlank(message = "Phone number is mandatory")
    @NotNull
    private String phoneNumber;

    private Boolean isAdmin;

    private Boolean active;
    
}
