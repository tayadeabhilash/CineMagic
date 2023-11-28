package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.mapper.UserMapper;
import com.scrumandcoke.movietheaterclub.model.UserEntity;
import com.scrumandcoke.movietheaterclub.model.enums.MemberType;
import com.scrumandcoke.movietheaterclub.repository.UserRepository;
import com.scrumandcoke.movietheaterclub.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(@NonNull CreateUserRequest createUserRequest) {
        if (userExistsByEmail(createUserRequest.getEmail())) {
            throw new IllegalArgumentException("User with the email " + createUserRequest.getEmail() + " already exists");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(createUserRequest.getFirstName());
        userEntity.setLastName(createUserRequest.getLastName());
        userEntity.setExternalId(UUID.randomUUID().toString());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setMemberType(MemberType.REGULAR);

        userRepository.save(userEntity);

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDto validateLoginCredentials(@NonNull String email, @NonNull String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    private boolean userExistsByEmail(@NonNull String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return Objects.nonNull(userEntity);
    }

    @Override
    public UserDto getUserByEmail(@NonNull String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            throw new NoSuchElementException("No user exists with the email " + email);
        }

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return UserMapper.INSTANCE.userEntitiesToDtos(userEntities);
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
