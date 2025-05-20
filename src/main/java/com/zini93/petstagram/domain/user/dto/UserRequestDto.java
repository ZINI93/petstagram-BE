package com.zini93.petstagram.domain.user.dto;


import lombok.Getter;

@Getter
public class UserRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickName;
    private String profileImageUrl;
    private String bio;

    public UserRequestDto(String email, String password, String firstName, String lastName, String nickName, String profileImageUrl, String bio) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
    }
}
