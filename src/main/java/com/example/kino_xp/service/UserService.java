package com.example.kino_xp.service;

import com.example.kino_xp.entity.User;
import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.exception.UserNotFoundException;
import com.example.kino_xp.repository.UserRepository;
import com.example.kino_xp.dto.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter){
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(int id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id: " + id + " could not be found");
        } else {
            return userConverter.toDTO(optionalUser.get());
        }
    }

    public List<UserDTO> getUsersByName(String email){
        List<User> userList = userRepository.findAllByEmail(email);

        if (userList.isEmpty()){
            throw new UserNotFoundException("User with email: " + email + " could not be found");
        } else {
            return userList.stream()
                    .map(userConverter::toDTO)
                    .collect(Collectors.toList());
        }
    }
}
