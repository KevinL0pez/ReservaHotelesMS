package org.reservahoteles.dto;

import lombok.Data;


@Data
public class UserDto {
    private Long idUser;
    
    private String numberDocumentUser;

    private String emailUser;

    private String passwordUser;

    private String namesUser;

    private String lastNamesUser;

    private String phoneNumber;

    private Boolean isAdmin;

    private Boolean active;
    
}
