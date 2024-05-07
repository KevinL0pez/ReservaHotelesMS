package org.reservahoteles.service;

import org.reservahoteles.dto.AuthenticationResponseDto;
import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.UserEntity;

public interface IAuthenticationService {
    ResponseDto<UserDto> register(UserDto userDto);
    ResponseDto<LoginRequestDto> authenticate(LoginRequestDto request);
}
