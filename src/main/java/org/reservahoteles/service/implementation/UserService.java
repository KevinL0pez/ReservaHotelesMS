package org.reservahoteles.service.implementation;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.UserEntity;
import org.reservahoteles.jpa.repositories.UserRepository;
import org.reservahoteles.service.IUserService;
import org.reservahoteles.utilities.PasswordBCryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordBCryptUtil passwordBCryptUtil;

    /**
     *
     *  @return
     */
    @Override
    @Transactional
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setIdUser(user.getIdUser());
                    userDto.setNumberDocumentUser(user.getNumberDocumentUser());
                    userDto.setEmailUser(user.getEmailUser());
                    userDto.setPasswordUser(user.getPasswordUser());
                    userDto.setNamesUser(user.getNamesUser());
                    userDto.setLastNamesUser(user.getLastNamesUser());
                    userDto.setPhoneNumber(user.getPhoneNumber());
                    userDto.setIsAdmin(user.getIsAdmin());
                    userDto.setActive(user.getActive());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto getUsersByDoc(String NumberDocumentUser) {
         UserEntity user = userRepository.findByNumberDocumentUser(NumberDocumentUser);
         if (user != null){
                UserDto userDto = new UserDto();
                userDto.setIdUser(user.getIdUser());
                userDto.setNumberDocumentUser(user.getNumberDocumentUser());
                userDto.setEmailUser(user.getEmailUser());
                userDto.setPasswordUser(user.getPasswordUser());
                userDto.setNamesUser(user.getNamesUser());
                userDto.setLastNamesUser(user.getLastNamesUser());
                userDto.setPhoneNumber(user.getPhoneNumber());
                userDto.setIsAdmin(user.getIsAdmin());
                userDto.setActive(user.getActive());
                return userDto;
         } else {
             return null;
         }

    }

    @Override
    @Transactional
    public ResponseDto createUser(@Valid UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        UserEntity userEntity = new UserEntity();

        UserEntity validateUser = userRepository.findEmailUser(userDto.getEmailUser());
        if (validateUser != null) {
            responseDto.setError(Boolean.TRUE);
            responseDto.setMessage("El correo ingresado se encuentra registrado");
            return responseDto;
        }

        try {
            responseDto.setError(Boolean.FALSE);
            responseDto.setMessage("Se ha creado el usuario correctamente.");
            userEntity.setNumberDocumentUser(userDto.getNumberDocumentUser());
            userEntity.setEmailUser(userDto.getEmailUser());
            userEntity.setPasswordUser(userDto.getPasswordUser());
//            userEntity.setPasswordUser(passwordBCryptUtil.encodePassword(userDto.getPasswordUser()));
            userEntity.setNamesUser(userDto.getNamesUser());
            userEntity.setLastNamesUser(userDto.getLastNamesUser());
            userEntity.setPhoneNumber(userDto.getPhoneNumber());
            userEntity.setIsAdmin(Boolean.FALSE);
            userEntity.setActive(Boolean.TRUE);

            userRepository.save(userEntity);
        } catch (Exception e) {
            responseDto.setError(Boolean.TRUE);
            responseDto.setMessage("Ha ocurrido un error interno, intenta de nuevo más tarde.");
            log.error("Ha ocurrido un error:: ", e);
        }

        return  responseDto;
    }

    @Override
    public ResponseDto validateUserCredentials(LoginRequestDto loginRequestDto) {
        UserEntity user = userRepository.findEmailUser(loginRequestDto.getEmailUser());
        ResponseDto loginResponseDto = new ResponseDto();
        if (user != null){
            if (user.getPasswordUser().equals(loginRequestDto.getPassword())){
                loginResponseDto.setError(false);
                loginResponseDto.setMessage("Se ha iniciado la sesión correctamente.");
                loginResponseDto.setStatus_code(200);
            }
            else{
                loginResponseDto.setError(true);
                loginResponseDto.setMessage("Credenciales de usuario invalidas");
                loginResponseDto.setStatus_code(401);
            }
        } else{
            loginResponseDto.setError(true);
            loginResponseDto.setMessage("Usuario no encontrado");
            loginResponseDto.setStatus_code(404);
        }

        return loginResponseDto;
    }

}
