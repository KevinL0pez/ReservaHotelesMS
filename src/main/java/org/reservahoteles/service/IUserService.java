package org.reservahoteles.service;

import org.reservahoteles.dto.LoginResponseDto;
import org.reservahoteles.dto.UserDto;

import java.util.List;

public interface IUserService {
    
    /**
     *
     *  @return
     */
    List<UserDto> getUsers();

    void createUser(UserDto userDto);

    UserDto getUsersByDoc(String NumberDocumentUser);


    LoginResponseDto validateUserCredentials(String EmailUser, String Password);
}
