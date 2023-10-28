package com.example.book_trade.infrastructure.member;

import com.example.book_trade.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(final String name);

    boolean existsByEmail(final String email);

    void deleteById(final Long id);

    Optional<Member> findById(final Long id);
}
