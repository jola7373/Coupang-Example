package com.example.demo.src.Member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostMemberReq {
    private String email;
    private String password;
    private String name;

    public Member toEntity(){
        return Member.builder().email(this.email)
                .password(this.password).name(this.name).build();
    }
}
