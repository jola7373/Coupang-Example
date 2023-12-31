package com.example.demo.src.auth;

import com.example.demo.src.Member.MemberRepository;
import com.example.demo.src.Member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String loginId = provider + "_" +providerId;

        Optional<Member> optionalUser = memberRepository.findByLoginId(loginId);
        System.out.println(oAuth2User);
        Member member;
        if(!optionalUser.isPresent()) {
            member = Member.builder()
                    .loginId(loginId)
                    .name(oAuth2User.getAttribute("name"))
                    .role("ROLE_USER")
                    .build();
            memberRepository.save(member);
        } else {
            member = optionalUser.get();
        }


        return new CurrentUserDetails(member, oAuth2User.getAttributes());
    }

}
