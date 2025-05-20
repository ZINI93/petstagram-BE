package com.zini93.petstagram.domain.user.service;

import com.zini93.petstagram.domain.user.dto.UserRequestDto;
import com.zini93.petstagram.domain.user.dto.UserResponseDto;
import com.zini93.petstagram.domain.user.dto.UserUpdateDto;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;
    @InjectMocks UserServiceImpl userService;

    User user;

    UserRequestDto requestDto;


    @BeforeEach
    void setup(){


        requestDto = new UserRequestDto(
                "user@gmail.com",
                "12341234",
                "park",
                "zini",
                "zini93",
                "www.image.com",
                "cute zini"
        );

        user = new User(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getNickName(),
                requestDto.getProfileImageUrl(),
                requestDto.getBio(),
                UUID.randomUUID().toString()
        );

    }


    @Test
    void createUserWithValidate() {
        //given
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn("password");
        when(userRepository.save(any(User.class))).thenReturn(user);


        //when
        UserResponseDto result = userService.createUserWithValidate(requestDto);

        //then
        assertNotNull(result);
        assertEquals(user.getEmail(),result.getEmail());
        assertEquals(user.getUserUuid(),result.getMemberUuid());

        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void getUserInfo() {

        //given
        when(userRepository.findByUserUuid(user.getUserUuid())).thenReturn(Optional.ofNullable(user));

        //when
        UserResponseDto result = userService.getUserInfo(user.getUserUuid());


        //then
        assertNotNull(result);
        assertEquals(user.getEmail(),result.getEmail());
        assertEquals(user.getUserUuid(),result.getMemberUuid());

        verify(userRepository,times(1)).findByUserUuid(user.getUserUuid());


    }


    @Test
    void updateUserWithValidate() {

        //given

        UserUpdateDto userUpdateDto = new UserUpdateDto("111111", "tanaka", "yuna", "yuyu", "www.image2.com", "cute yuna");
        when(userRepository.findByUserUuid(user.getUserUuid())).thenReturn(Optional.ofNullable(user));

        //when
        UserResponseDto result = userService.updateUserWithValidate(user.getUserUuid(), userUpdateDto);

        //then
        assertNotNull(result);
        assertEquals(userUpdateDto.getFirstName(),result.getFirstName());
        assertEquals(userUpdateDto.getProfileImageUrl(),result.getProfileImageUrl());

        verify(userRepository,times(1)).findByUserUuid(user.getUserUuid());

    }

    @Test
    void deleteUser() {

        //given

        when(userRepository.findByUserUuid(user.getUserUuid())).thenReturn(Optional.ofNullable(user));


        //when
        userService.deleteUser(user.getUserUuid());

    }
}