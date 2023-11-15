package com.example.demo.src.Member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchMemberReq {
    private int id;
    private String name;
}
