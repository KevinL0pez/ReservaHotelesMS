package org.reservahoteles.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.reservahoteles.dto.AuthenticationResponseDto;
import org.reservahoteles.dto.LoginRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.TokenEntity;
import org.reservahoteles.jpa.entities.UserEntity;
import org.reservahoteles.jpa.repositories.TokenRepository;
import org.reservahoteles.jpa.repositories.UserRepository;
import org.reservahoteles.service.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ResponseDto register(UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        // check if user already exist. if exist than authenticate the user
        UserEntity validateUser = userRepository.findEmailUser(userDto.getEmailUser());
        if (validateUser != null) {
            responseDto.setError(Boolean.TRUE);
            responseDto.setMessage("El correo ingresado se encuentra registrado");
            responseDto.setStatusCode(HttpStatus.OK.value());
            return responseDto;
        }

        try {
            UserEntity userEntity = new UserEntity();

            userEntity.setNumberDocumentUser(userDto.getNumberDocumentUser());
            userEntity.setEmailUser(userDto.getEmailUser());
            userEntity.setNamesUser(userDto.getNamesUser());
            userEntity.setLastNamesUser(userDto.getLastNamesUser());
            userEntity.setPhoneNumber(userDto.getPhoneNumber());
            userEntity.setActive(Boolean.TRUE);
            userEntity.setRole(userDto.getRole());
            userEntity.setUsername(userDto.getEmailUser());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPasswordUser()));
            userEntity = userRepository.save(userEntity);
            String jwt = jwtService.generateToken(userEntity);
            saveUserToken(jwt, userEntity);
            responseDto.setError(Boolean.FALSE);
            responseDto.setMessage("Se ha creado el usuario correctamente.");
            responseDto.setStatusCode(HttpStatus.CREATED.value());
        } catch (Exception e) {
            responseDto.setError(Boolean.TRUE);
            responseDto.setMessage("Ha ocurrido un error interno, intenta de nuevo más tarde.");
            log.error("Ha ocurrido un error:: ", e);
        }
        return responseDto;
    }

    @Override
    public ResponseDto authenticate(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailUser(),
                        request.getPassword()
                )
        );

        ResponseDto loginResponseDto = new ResponseDto();
        UserEntity userEntity = userRepository.findEmailUser(request.getEmailUser());
        String jwt = jwtService.generateToken(userEntity);
        revokeAllTokenByUser(userEntity);
        saveUserToken(jwt, userEntity);

        if (passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            loginResponseDto.setError(Boolean.FALSE);
            loginResponseDto.setToken(jwt);
            loginResponseDto.setMessage("Se ha iniciado la sesión correctamente.");
            loginResponseDto.setStatusCode(HttpStatus.OK.value());
        } else {
            loginResponseDto.setError(Boolean.TRUE);
            loginResponseDto.setMessage("Credenciales de usuario invalidas.");
            loginResponseDto.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return loginResponseDto;
    }

    private void revokeAllTokenByUser(UserEntity user) {
        List<TokenEntity> validTokens = tokenRepository.findAllTokensByUser(Math.toIntExact(user.getIdUser()));
        if (validTokens.isEmpty()) {
            return;
        }

        for (TokenEntity token : validTokens) {
            token.setLoggedOutDate(new Date());
        }

        validTokens.forEach(t -> t.setLoggedOut(true));
        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, UserEntity user) {
        TokenEntity token = new TokenEntity();
        token.setToken(jwt);
        token.setLoggedOut(Boolean.FALSE);
        token.setUser(user);
        token.setRegisteredTokenDate(new Date());
        tokenRepository.save(token);
    }
}
