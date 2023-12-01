package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.entity.UserEntity;
import com.scrumandcoke.movietheaterclub.enums.MemberType;
import com.scrumandcoke.movietheaterclub.enums.TransactionType;
import com.scrumandcoke.movietheaterclub.enums.UserType;
import com.scrumandcoke.movietheaterclub.mapper.UserMapper;
import com.scrumandcoke.movietheaterclub.repository.UserRepository;
import com.scrumandcoke.movietheaterclub.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(@NonNull CreateUserRequest createUserRequest, @NonNull UserType userType) {
        if (userExistsByEmail(createUserRequest.getEmail())) {
            throw new IllegalArgumentException("User with the email " + createUserRequest.getEmail() + " already exists");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(createUserRequest.getFirstName());
        userEntity.setLastName(createUserRequest.getLastName());
        userEntity.setExternalId(UUID.randomUUID().toString());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        userEntity.setMemberType(MemberType.REGULAR);
        userEntity.setUserType(userType);
        userEntity.setPoints(0.0);
        userRepository.save(userEntity);

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDto createUser(@NonNull CreateUserRequest createUserRequest) {
        return createUser(createUserRequest, UserType.MEMBER);
    }

    @Override
    public UserDto validateLoginCredentials(@NonNull String email, @NonNull String password) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException("Invalid Credentials");
        }

        validatePasswordMatches(password, userEntity.get().getPassword());

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity.get());
    }

    @Override
    public UserDto updateMemberType(@NonNull String userId, @NonNull MemberType newMemberType) {
        Optional<UserEntity> userEntity = userRepository.findByExternalId(userId);
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException("No user found with the userId " + userId);
        }

        if (UserType.THEATER_EMPLOYEE.equals(userEntity.get().getUserType())) {
            throw new IllegalArgumentException("User of the type THEATER_EMPLOYEE cannot update membership type");
        }

        userEntity.get().setMemberType(newMemberType);
        userRepository.save(userEntity.get());

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity.get());
    }

    @Override
    public UserDto updatePoints(@NonNull String userId, @NonNull Double points, TransactionType transactionType) {
        Optional<UserEntity> userEntity = userRepository.findByExternalId(userId);
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException("No user found with the userId " + userId);
        }
        Double newPoints = userEntity.get().getPoints();
        if(transactionType == TransactionType.DEBIT)
            newPoints = newPoints - points;
        else
            newPoints = newPoints + points;
        userEntity.get().setPoints(newPoints);
        userRepository.save(userEntity.get());

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity.get());
    }

    private boolean userExistsByEmail(@NonNull String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent();
    }

    @Override
    public UserDto getUserByEmail(@NonNull String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException("No user found with the email " + email);
        }

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity.get());
    }

    @Override
    public UserDto getUserByUserId(@NonNull String userId) {
        Optional<UserEntity> userEntity = userRepository.findByExternalId(userId);
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException("No user exists with the userId " + userId);
        }

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity.get());
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return UserMapper.INSTANCE.userEntitiesToDtos(userEntities);
    }

    @Override
    public void updateUser(@NonNull UserDto userDto) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException("No user found with the email " + userDto.getEmail());
        }


        userEntity.get().setFirstName(userDto.getFirstName());
        userEntity.get().setLastName(userDto.getLastName());

        userRepository.save(userEntity.get());
    }

    @Override
    public void deleteUser(@NonNull Integer id) {
        userRepository.deleteById(id);
    }


    private void validatePasswordMatches(@NonNull String inputPassword, @NonNull String storedPassword) {
        if (!passwordEncoder.matches(inputPassword, storedPassword)) {
            throw new IllegalArgumentException("Invalid Credentials");
        }
    }
}
