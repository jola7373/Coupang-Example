package com.example.demo.src.Member;


import com.example.demo.src.Member.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByName(String name);
    boolean existsByEmail(String email);

    Optional<Member> findByLoginId(String loginId);
}
