package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.model.UserEntity;
import com.scrumandcoke.movietheaterclub.repository.UserRepository;
import com.scrumandcoke.movietheaterclub.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setCreatedAt(Date.from(Instant.now()));
        userEntity.setLastUpdatedAt(Date.from(Instant.now()));
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setMemberType(createUserRequest.getMemberType());

        userRepository.save(userEntity);

        return new UserDto();
    }

    @Override
    public UserDto validateLoginCredentials(@NonNull String email, @NonNull String password) {
        UserEntity userEntity = userRepository.findByEmail(email);

        return new UserDto();
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

        return new UserDto();
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> response = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            response.add(new UserDto(String.valueOf(userEntity.getId()), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), null, userEntity.getMemberType()));
        }
        return response;
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setLastUpdatedAt(Date.from(Instant.now()));
        userEntity.setPassword(userDto.getPassword());
        userEntity.setMemberType(userDto.getMemberType());
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
