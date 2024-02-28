
package org.marketplace.controllers;

import lombok.RequiredArgsConstructor;
import org.marketplace.dto.CategoriesDto;
import org.marketplace.service.ICategoriesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

        private final IUserService iUserService;

        @RequestMapping("/all")
        public List<UserDto> getListUsers() {
            return iUserService.getUsers();
        }
        

    
}
