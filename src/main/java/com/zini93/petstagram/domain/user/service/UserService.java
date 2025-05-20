package com.zini93.petstagram.domain.user.service;

import com.zini93.petstagram.domain.user.dto.UserRequestDto;
import com.zini93.petstagram.domain.user.dto.UserResponseDto;
import com.zini93.petstagram.domain.user.dto.UserUpdateDto;
import com.zini93.petstagram.domain.user.entity.User;

public interface UserService {

    User createUser(UserRequestDto request);
    UserResponseDto createUserWithValidate(UserRequestDto requestDto);
    UserResponseDto getUserInfo(String userUuid);
    UserResponseDto updateUserWithValidate(String userUuid, UserUpdateDto updateDto);
    void deleteUser(String userUuid);

}
