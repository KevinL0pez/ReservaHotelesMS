
package org.reservahoteles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.UserEntity;
import org.reservahoteles.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Log4j2
public class UserController {

        private final IUserService iUserService;

        @GetMapping("getuser/all")
        public List<UserDto> getListUsers() {
            return iUserService.getUsers();
        }

        @PostMapping("/createuser")
        public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devolver mensajes de error
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
            try {
                iUserService.createUser(userDto);
                return ResponseEntity.ok("Creado");
            } catch (Exception ex){
                log.error(ex);
                return ResponseEntity.internalServerError().body(ex);
            }
        }

        @GetMapping("/getuser")
        public UserDto getUserByDoc(@RequestParam String documentUser) {
            return iUserService.getUsersByDoc(documentUser);
        }

}
