package org.reservahoteles.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String token;
    private Boolean error;
    private String message;
    private Integer statusCode;
}
