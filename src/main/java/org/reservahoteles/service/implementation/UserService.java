package org.marketplace.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    /**
     *
     *  @return
     */
    @Override
    @Transactional
    public List<UserDto> getUsers() {
        UserEntity userEntity = new UserEntity();

        userEntity.setNumber_document_user("1092175096");
        userEntity.setEmail_user("prueba@gmail.com");
        userEntity.setPassword_user("12345");
        userEntity.setNames_user("kevin andres");
        userEntity.setLastnames_user("usama");
        userEntity.setPhone_number(301234623);
        userEntity.setIs_admin(true);
        userEntity.setActive(true);

        userRepository.save(userEntity);

        return userRepository.findAll().stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId_user(user.getId_user());
                    userDto.setNumber_document_user(user.getNumber_document_user());
                    userDto.setEmail_user(user.getEmail_user());
                    userDto.setPassword_user(user.getPassword_user());
                    userDto.setNames_user(user.getNames_user());
                    userDto.setLastnames_user(user.getLastnames_user());
                    userDto.setPhone_number(user.getPhone_number());
                    userDto.setIs_admin(user.getIs_admin());
                    userDto.setActive(user.getActive());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

}
