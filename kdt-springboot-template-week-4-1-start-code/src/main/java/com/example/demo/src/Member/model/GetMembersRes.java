package com.example.demo.src.Member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class GetMembersRes {
    List<GetMemberRes> userResList;

    public static GetMembersRes listof(List<Member> members) {
        return new GetMembersRes(members.stream().map(GetMemberRes::of).collect(Collectors.toList()));
    }
}
