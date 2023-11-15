package com.example.demo.src.Member.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GetMemberRes {
    private int id;
    private String email;
    private String password;
    private String name;

    public static GetMemberRes of(Member member) {
        return GetMemberRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .build();
    }
}
