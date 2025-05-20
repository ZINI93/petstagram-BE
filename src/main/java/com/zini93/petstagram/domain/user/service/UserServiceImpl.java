package com.zini93.petstagram.domain.user.service;

import com.zini93.petstagram.domain.user.dto.UserRequestDto;
import com.zini93.petstagram.domain.user.dto.UserResponseDto;
import com.zini93.petstagram.domain.user.dto.UserUpdateDto;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserUuidNotFoundException;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRequestDto request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .nickName(request.getNickName())
                .profileImageUrl(request.getProfileImageUrl())
                .bio(request.getBio())
                .build();

    }

    @Override
    public UserResponseDto createUserWithValidate(UserRequestDto requestDto) {

        User user = createUser(requestDto);
        User savedUser = userRepository.save(user);

        log.info("Created user for user email:{}", user.getEmail());

        return savedUser.toResponse();
    }

    @Override
    public UserResponseDto getUserInfo(String userUuid) {
        User user = findUserByUserUuidInUser(userUuid);
        log.info("Searching user for user email:{}", user.getEmail());

        return user.toResponse();
    }

    public void updateUser(User user, UserUpdateDto updateDto){

        String encodedPassword = passwordEncoder.encode(updateDto.getPassword());

        user.UpdateUser(
                encodedPassword,
                updateDto.getFirstName(),
                updateDto.getLastName(),
                updateDto.getNickName(),
                updateDto.getProfileImageUrl(),
                updateDto.getBio());
    }

    @Override
    public UserResponseDto updateUserWithValidate(String userUuid, UserUpdateDto updateDto) {

        User user = findUserByUserUuidInUser(userUuid);
        log.info("Updating user for user email:{}", user.getEmail());

        updateUser(user,updateDto);
        log.info("updated user for user email:{}",user.getEmail());

        return user.toResponse();
    }

    @Override
    public void deleteUser(String userUuid) {

        User user = findUserByUserUuidInUser(userUuid);
        log.info("deleting user for user email:{}", user.getEmail());

        userRepository.delete(user);
    }

    private User findUserByUserUuidInUser(String userUuid) {
        return userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new UserUuidNotFoundException("userUuid: User Not found"));
    }
}
