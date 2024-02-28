
package org.reservahoteles.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Log4j2
public class UserController {

        private final IUserService iUserService;

        @GetMapping("/all")
        public List<UserDto> getListUsers() {
            return iUserService.getUsers();
        }

        @PostMapping("/createuser")
        public ResponseEntity<Boolean> createUser(@RequestBody UserDto userDto) {
            try {
                iUserService.createUser(userDto);
                return ResponseEntity.ok(true);
            } catch (Exception ex){
                log.error(ex);
                return ResponseEntity.internalServerError().body(false);
            }
        }
    
}
