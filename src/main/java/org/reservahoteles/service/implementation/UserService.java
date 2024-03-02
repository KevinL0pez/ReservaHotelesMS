package org.reservahoteles.service.implementation;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.entities.UserEntity;
import org.reservahoteles.jpa.repositories.UserRepository;
import org.reservahoteles.service.IUserService;
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
    public void createUser(@Valid UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setNumberDocumentUser(userDto.getNumberDocumentUser());
        userEntity.setEmailUser(userDto.getEmailUser());
        userEntity.setPasswordUser(userDto.getPasswordUser());
        userEntity.setNamesUser(userDto.getNamesUser());
        userEntity.setLastNamesUser(userDto.getLastNamesUser());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setIsAdmin(userDto.getIsAdmin());
        userEntity.setActive(userDto.getActive());

        userRepository.save(userEntity);
    }

}
