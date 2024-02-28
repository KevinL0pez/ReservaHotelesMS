package org.reservahoteles.service;

import org.reservahoteles.dto.UserDto;

import java.util.List;

public interface IUserService {
    
    /**
     *
     *  @return
     */
    List<UserDto> getUsers();

    void createUser(UserDto userDto);

}
