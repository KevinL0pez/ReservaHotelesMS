package org.reservahoteles.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.service.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Log4j2
public class LoginController {

    private final IUserService iUserService;

    @CrossOrigin("*")
    @PostMapping("login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return iUserService.validateUserCredentials(loginRequestDto);

    }


}
