package org.reservahoteles.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Set;

@Data
public class ResponseDto<T> {
    private String token;
    private Boolean error;
    private String message;
    private HttpStatus statusCode;
    private T data;
}
