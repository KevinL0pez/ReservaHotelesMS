package org.marketplace.service;

import org.marketplace.dto.UserDto;

import java.util.List;

public interface IUserService {
    
    /**
     *
     *  @return
     */
    List<UserDto> getUsers();

}
