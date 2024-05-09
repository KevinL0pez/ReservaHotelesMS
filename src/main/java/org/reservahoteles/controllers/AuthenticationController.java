package org.reservahoteles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.reservahoteles.dto.*;
import org.reservahoteles.service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;

    @CrossOrigin("*")
    @PostMapping("register")
    public ResponseEntity<ResponseDto<UserDto>> register(@RequestBody UserDto userDto) {

        ResponseDto<UserDto> responseDto = iAuthenticationService.register(userDto);
        return ResponseEntity.status(responseDto.getStatusCode()).body(responseDto);
    }

    @CrossOrigin("*")
    @PostMapping("login")
    public ResponseEntity<ResponseDto<LoginRequestDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        ResponseDto<LoginRequestDto> responseDto = iAuthenticationService.authenticate(loginRequestDto);
        return ResponseEntity.status(responseDto.getStatusCode()).body(responseDto);
    }
}
