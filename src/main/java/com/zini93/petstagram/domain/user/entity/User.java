package com.zini93.petstagram.domain.user.entity;

import com.zini93.petstagram.domain.BaseEntity;
import com.zini93.petstagram.domain.user.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    private String bio;

    @Column(name = "member_uuid", nullable = false, updatable = false)
    private String userUuid;

    @Column(name = "user_role", nullable = false)
    private UserRole userRole;


    @Builder
    public User(String email, String password, String firstName, String lastName, String nickName, String profileImageUrl, String bio, String userUuid) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
        this.userUuid = UUID.randomUUID().toString();
        this.userRole = UserRole.USER;
    }

    public void UpdateUser(String password, String firstName, String lastName, String nickName, String profileImageUrl, String bio) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
    }

    public UserResponseDto toResponse(){
        return UserResponseDto.builder()
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .nickName(this.nickName)
                .profileImageUrl(this.profileImageUrl)
                .bio(this.bio)
                .memberUuid(this.userUuid)
                .build();
    }
}
