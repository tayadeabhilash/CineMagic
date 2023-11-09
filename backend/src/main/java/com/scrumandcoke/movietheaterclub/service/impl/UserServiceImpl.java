package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.UserEntity;
import com.scrumandcoke.movietheaterclub.repository.UserRepository;
import com.scrumandcoke.movietheaterclub.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void addUser(UserDto userDto) throws GlobalException {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setCreatedAt(Date.from(Instant.now()));
            userEntity.setLastUpdatedAt(Date.from(Instant.now()));
            userEntity.setPassword(userDto.getPassword());
            userEntity.setMemberType(userDto.getMemberType());
            userRepository.save(userEntity);
        } catch (Exception exception) {
            logger.error("Error saving user: {}", userDto.getEmail());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public UserDto getUserByEmail(String email) throws GlobalException {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            return new UserDto(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), null, userEntity.getMemberType());
        } catch (Exception exception) {
            logger.error("Error getting user: {}", email);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<UserDto> getUsers() throws GlobalException {
        try {
            List<UserEntity> userEntities = userRepository.findAll();
            List<UserDto> response =new ArrayList<>();
            for(UserEntity userEntity : userEntities) {
                response.add(new UserDto(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), null, userEntity.getMemberType()));
            }
            return response;
        } catch (Exception exception) {
            logger.error("Error getting users");
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void updateUser(UserDto userDto) throws GlobalException {
        try {
            UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setLastUpdatedAt(Date.from(Instant.now()));
            userEntity.setPassword(userDto.getPassword());
            userEntity.setMemberType(userDto.getMemberType());
            userRepository.save(userEntity);
        } catch (Exception exception) {
            logger.error("Error updating user: {}", userDto.getEmail());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void deleteUser(Integer id) throws GlobalException {
        try {
            userRepository.deleteById(id);
        } catch (Exception exception) {
            logger.error("Error deleting user: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }
}
