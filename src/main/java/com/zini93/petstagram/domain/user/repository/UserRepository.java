package com.zini93.petstagram.domain.user.repository;

import com.zini93.petstagram.domain.user.dto.UserResponseDto;
import com.zini93.petstagram.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserUuid(String userUuid);
  Optional<User> findByEmail(String email);
  }