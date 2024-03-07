package org.reservahoteles.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private Boolean error;
    private String message;
    private Integer status_code;
}
