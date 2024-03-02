package org.reservahoteles.service;

import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.UserEntity;

import java.util.List;

public interface IUserService {
    
    /**
     *
     *  @return
     */
    List<UserDto> getUsers();

    void createUser(UserDto userDto);

    UserDto getUsersByDoc(String NumberDocumentUser);

}
