package com.zini93.petstagram.domain.user.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {


    private String email;
    private String firstName;
    private String lastName;
    private String nickName;
    private String profileImageUrl;
    private String bio;
    private String memberUuid;

    @Builder
    public UserResponseDto(String email, String firstName, String lastName, String nickName, String profileImageUrl, String bio, String memberUuid) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
        this.memberUuid = memberUuid;
    }
}
