package com.example.book_trade.infrastructure.member;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.Sex;
import com.example.book_trade.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberRepositoryAdapterTest {

    private MemberJpaRepository memberJpaRepository;
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberJpaRepository = Mockito.mock(MemberJpaRepository.class); // Mock 객체 생성
        memberRepository = new MemberRepositoryAdapter(memberJpaRepository);}

    @Test
    @DisplayName("save test")
    void saveTest() {
        //given
        Member memberToSave = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        Member savedMember = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        Mockito.when(memberJpaRepository.save(memberToSave)).thenReturn(savedMember);

        //when
        Member result = memberRepository.save(memberToSave);

        //then
        assertEquals(result, savedMember);
    }

    @Test
    @DisplayName("findByEmail test")
    void findByEmailTest() {
        //given
        String email = "keaam12@gmail.com";
        Member member = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        Mockito.when(memberJpaRepository.findByEmail(email)).thenReturn(Optional.of(member));

        //when
        Optional<Member> result = memberRepository.findByEmail(email);

        //then
        assertTrue(result.isPresent());
        assertEquals(member, result.get()); // 예상 결과와 실제 결과 비교
    }

    @Test
    @DisplayName("existsByEmail test")
    void existsByEmailTest() {
        //given
        String email = "keaam12@gmail.com";
        Mockito.when(memberJpaRepository.existsByEmail(email)).thenReturn(true);

        //when
        boolean result = memberRepository.existsByEmail(email);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("deleteById test")
    void deleteByIdTest() {
        //given
        Long memberId = 1L;
        Mockito.doNothing().when(memberJpaRepository).deleteById(memberId);

        //when
        memberRepository.deleteById(memberId);
    }

    @Test
    @DisplayName("findById test")
    void findByIdTest() {
        //given
        Long memberId = 1L;
        Member expectedMember = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        Mockito.when(memberJpaRepository.findById(memberId)).thenReturn(Optional.of(expectedMember));

        //when
        Optional<Member> result = memberRepository.findById(memberId);

        //then
        assertTrue(result.isPresent());
        assertEquals(expectedMember, result.get());
    }
}
