package org.reservahoteles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.reservahoteles.dto.AuthenticationResponseDto;
import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;

    @CrossOrigin("*")
    @PostMapping("register")
    public ResponseEntity<ResponseDto> register(
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(iAuthenticationService.register(userDto));
    }

    @CrossOrigin("*")
    @PostMapping("login")
    public ResponseEntity<ResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        return ResponseEntity.ok(iAuthenticationService.authenticate(loginRequestDto));
    }
}
