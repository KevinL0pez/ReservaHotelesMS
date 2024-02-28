package org.marketplace.dto;

import lombok.Data;


@Data
public class UserDto {
    private Long id_user;
    
    private String number_document_user;

    private String email_user;

    private String password_user;

    private String names_user;

    private String lastnames_user;

    private Integer phone_number;

    private Boolean is_admin;

    private Boolean active;
    
}
