package com.project.mission_school.mypage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class MypageUser {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String phonenumber;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = true)
    private String village;

    @Builder
    public MypageUser(Long id, String username, String password, String phonenumber, String nickname, String village) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.nickname = nickname;
        this.village = village;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
