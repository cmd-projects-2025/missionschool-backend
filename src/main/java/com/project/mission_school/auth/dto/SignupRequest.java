package com.project.mission_school.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
    private String username;
    private String nickname;
    private String password;
    private String phonenumber;
    private String village;
}
