package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.User;
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
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setCreatedAt(Date.from(Instant.now()));
            user.setLastUpdatedAt(Date.from(Instant.now()));
            user.setPassword(userDto.getPassword());
            user.setMemberType(userDto.getMemberType());
            userRepository.save(user);
        } catch (Exception exception) {
            logger.error("Error saving user: {}", userDto.getEmail());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public UserDto getUserByEmail(String email) throws GlobalException {
        try {
            User user = userRepository.findByEmail(email);
            return new UserDto(user.getFirstName(), user.getLastName(), user.getEmail(), null, user.getMemberType());
        } catch (Exception exception) {
            logger.error("Error getting user: {}", email);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<UserDto> getUsers() throws GlobalException {
        try {
            List<User> users = userRepository.findAll();
            List<UserDto> response =new ArrayList<>();
            for(User user: users) {
                response.add(new UserDto(user.getFirstName(), user.getLastName(), user.getEmail(), null, user.getMemberType()));
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
            User user = userRepository.findByEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setLastUpdatedAt(Date.from(Instant.now()));
            user.setPassword(userDto.getPassword());
            user.setMemberType(userDto.getMemberType());
            userRepository.save(user);
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
