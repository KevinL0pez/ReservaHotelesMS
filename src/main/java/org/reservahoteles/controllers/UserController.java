
package org.reservahoteles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.UserEntity;
import org.reservahoteles.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Log4j2
public class UserController {

        private final IUserService iUserService;

        @GetMapping("/getuser/all")
        public List<UserDto> getListUsers() {
            return iUserService.getUsers();
        }

        @CrossOrigin("*")
        @PostMapping("/createuser")
        public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
            ResponseDto response = new ResponseDto();

            // Si hay errores de validación, devolver mensajes de error
            if (bindingResult.hasErrors()) {
                List<String> errors = new ArrayList<>();
                bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
                response.setMessage(errors.toString());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            try {
                response = iUserService.createUser(userDto);
                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setMessage("Error interno al crear el usuario.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }

        @GetMapping("/getuser")
        public UserDto getUserByDoc(@RequestParam String documentUser) {
            return iUserService.getUsersByDoc(documentUser);
        }

}
