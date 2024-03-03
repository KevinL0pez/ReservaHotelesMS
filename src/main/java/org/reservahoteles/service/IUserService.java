package org.reservahoteles.service;

import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.UserDto;

import java.util.List;

public interface IUserService {
    
    /**
     *
     *  @return
     */
    List<UserDto> getUsers();

    ResponseDto createUser(UserDto userDto);

    UserDto getUsersByDoc(String NumberDocumentUser);


    ResponseDto validateUserCredentials(LoginRequestDto loginRequestDto);
}
