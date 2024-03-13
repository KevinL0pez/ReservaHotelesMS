package org.reservahoteles.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Log4j2
public class LoginController {

    private final IUserService iUserService;

    @CrossOrigin("*")
    @PostMapping("login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        ResponseDto loginResponseDto = iUserService.validateUserCredentials(loginRequestDto);
        if (loginResponseDto.getStatus_code() == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }else if (loginResponseDto.getStatus_code() == 401){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponseDto);
        }else if (loginResponseDto.getStatus_code() == 404){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginResponseDto);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResponseDto);
        }

    }


}
