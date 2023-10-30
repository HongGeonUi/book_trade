package com.example.book_trade.domain.member.repository;

import com.example.book_trade.domain.member.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(final Member member);

    Optional<Member> findByEmail(final String email);

    boolean existsByEmail(final String email);

    void deleteById(final Long id);

    Optional<Member> findById(final Long id);
}
