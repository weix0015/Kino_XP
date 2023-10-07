package com.example.kino_xp.service;

import com.example.kino_xp.converter.UserConverter;
import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.model.User;
import com.example.kino_xp.exception.UserNotFoundException;
import com.example.kino_xp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceMockTest {

    @Mock
    private UserRepository mockedUserRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    TicketService ticketService;

    UserService userService;


    User userToSave = new User(
            3,
            "Peter",
            "Peter@mail.com",
            "somecoolpassword",
            null,
            false
    );


    //ARRANGE
    @BeforeEach
    void init() {
        userService = new UserService(mockedUserRepository, userConverter, ticketService);
        User user1 = new User();
        user1.setId(1);
        user1.setName("Mikkel");
        user1.setEmail("mikkel@email.com");
        user1.setPassword("WhatACoolPassword");
        user1.setTickets(null);
        user1.setAdmin(false);

        User user2 = new User();
        user2.setId(2);
        user2.setName("Martin");
        user2.setEmail("martin@email.com");
        user2.setPassword("YouKnowWhatThatsAPrettyNeatPassword");
        user2.setTickets(null);
        user2.setAdmin(true);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        //MOCK SETUP

        //GetAllUsers
        Mockito.when(mockedUserRepository.findAll()).thenReturn(userList);

        //GetUserById
        Mockito.when(mockedUserRepository.findById(1)).thenReturn(Optional.of(user1));
        Mockito.when(mockedUserRepository.findById(2)).thenReturn(Optional.of(user2));
        Mockito.when(mockedUserRepository.findById(42)).thenReturn(Optional.empty());

        //GetUsersByName
        Mockito.when(mockedUserRepository.findAllByName("Mikkel")).thenReturn(new ArrayList<>(Arrays.asList(user1)));
        Mockito.when(mockedUserRepository.findAllByName("Martin")).thenReturn(new ArrayList<>(Arrays.asList(user2)));

        //GetUsersByEmail
        Mockito.when(mockedUserRepository.findAllByEmail("mikkel@email.com")).thenReturn(new ArrayList<>(Arrays.asList(user1)));
        Mockito.when(mockedUserRepository.findAllByEmail("martin@email.com")).thenReturn(new ArrayList<>(Arrays.asList(user2)));

        //Create/Update User
        Mockito.when(mockedUserRepository.save(Mockito.any(User.class))).thenReturn(userToSave);

        //DeleteUserById
        Mockito.doNothing().when(mockedUserRepository).deleteById(1);
    }

    @Test
    void getAllUsers() {
        //ACT
        List<UserDTO> userDTOList = userService.getAllUsers();

        //ASSERT
        assertEquals("Mikkel", userDTOList.get(0).name());
        assertEquals("Martin", userDTOList.get(1).name());
    }

    @Test
    void getUserById() {
        //ACT
        UserDTO userDTO1 = userService.getUserById(1);
        UserDTO userDTO2 = userService.getUserById(2);

        //ASSERT
        assertEquals("Mikkel", userDTO1.name());
        assertEquals("Martin", userDTO2.name());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(42));

    }

    @Test
    void getUsersByEmail() {
        //ACT
        UserDTO userDTO1 = userService.getUserByEmail("mikkel@email.com");
        UserDTO userDTO2 = userService.getUserByEmail("martin@email.com");

        //ASSERT
        assertEquals("Mikkel", userDTO1.name());
        assertEquals("Martin", userDTO2.name());

        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("doesNotExist@email.com"));
    }

    @Test
    void getUserByName() {
        //ACT
        UserDTO u1 = userService.getUsersByName("Mikkel").get(0);
        UserDTO u2 = userService.getUsersByName("Martin").get(0);

        //ASSERT
        assertEquals("Mikkel", u1.name());
        assertEquals("Martin", u2.name());

        assertThrows(UserNotFoundException.class, () -> userService.getUsersByName("Bjarne"));
    }

    @Test
    void createUser() {
        //ACT
        UserDTO userDTO = userService.createUser(userConverter.toDTO(userToSave));

        //ASSERT
        assertEquals(userConverter.toDTO(userToSave), userDTO);
    }

    @Test
    void updateUser() {
        //ACT
        UserDTO userDTO = userService.updateUser(1, userConverter.toDTO(userToSave));

        //ASSERT
        assertEquals(userConverter.toDTO(userToSave), userDTO);
    }

    @Test
    void deleteUserById() {
        //ACT
        userService.deleteUserById(1);

        //ASSERT
        Mockito.verify(mockedUserRepository).deleteById(1);
    }
}