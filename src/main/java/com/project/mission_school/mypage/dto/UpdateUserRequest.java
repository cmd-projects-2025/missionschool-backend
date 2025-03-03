package com.project.mission_school.mypage.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private final String username;
    private final String password;
    private final String phonenumber;
    private final String nickname;
    private final String village;

    public UpdateUserRequest(String username, String password, String phonenumber, String nickname, String village) {
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.nickname = nickname;
        this.village = village;
    }
}
