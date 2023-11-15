package com.example.demo.src.auth;

import com.example.demo.src.Member.model.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CurrentUserDetails implements UserDetails, OAuth2User {
    private Member member;
    private Map<String, Object> attributes;

    public CurrentUserDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    public CurrentUserDetails(Member member){
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println(member.getRole());
                return member.getRole();
            }
        });
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
