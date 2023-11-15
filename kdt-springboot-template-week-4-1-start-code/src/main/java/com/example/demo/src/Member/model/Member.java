package com.example.demo.src.Member.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
public class Member implements Serializable {
    @Id
    private int id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String loginId;

    @Builder
    public Member(String email, String name, String password, String loginId, String role){
        this.email = email;
        this.name = name;
        this.password = password;
        this.loginId = loginId;
        this.role = role;
    }

    public void modifyUserName(String name){
        this.name = name;
    }
}
