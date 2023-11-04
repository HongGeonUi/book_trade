package com.example.book_trade.application.member;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.PasswordManager;
import com.example.book_trade.domain.member.Sex;
import com.example.book_trade.domain.member.repository.MemberRepository;
import com.example.book_trade.presentation.member.dto.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MemberServiceTest {

    private MemberRepository memberRepository;
    private MemberService memberService;
    private PasswordManager passwordManager;

    @BeforeEach
    void setUp() {
        memberRepository = Mockito.mock(MemberRepository.class);
        passwordManager = Mockito.mock(PasswordManager.class);
        memberService = new MemberService(memberRepository, passwordManager);
    }

    @Test
    @DisplayName("signup Test")
    void testSignup() {
        //given
        SignupRequest req = new SignupRequest("keaam12@gmail.com", "aaaAAA111!!!", "홍건의", Sex.MAN);
        when(memberRepository.existsByEmail(req.email())).thenReturn(false);

        //when
        assertDoesNotThrow(() -> memberService.signup(req));

        //then
        Mockito.verify(memberRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("signup Test When emailExists")
    void testSignupExistingEmail() {
        //given
        SignupRequest req = new SignupRequest("keaam12@gmail.com", "aaaAAA111!!!", "홍건의", Sex.WOMAN);
        when(memberRepository.existsByEmail(req.email())).thenReturn(true);

        //when
        assertThrows(RuntimeException.class, () -> memberService.signup(req));

        //then
        Mockito.verify(memberRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("updateMember Test")
    void testUpdateMember() {
        //given
        Member existingMember = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        when(memberRepository.findByEmail("keaam12@gmail.com")).thenReturn(Optional.of(existingMember));

        //when
        assertDoesNotThrow(() -> memberService.updateMember("keaam12@gmail.com", "bbbBBB222@@@", "홍길동", Sex.WOMAN));

        //then
        assertEquals("bbbBBB222@@@", existingMember.getPassword());
        assertEquals("홍길동", existingMember.getName());
        assertEquals(Sex.WOMAN, existingMember.getSex());
    }

    @Test
    @DisplayName("updateMember Test When emailNonExists")
    void testUpdateMemberNonExistingEmail() {
        //given
        when(memberRepository.findByEmail("keaam12@gmail.com")).thenReturn(Optional.empty());

        //then
        assertThrows(RuntimeException.class, () -> memberService.updateMember("keaam12@gmail.com", "bbbBBB222@@@", "홍길동", Sex.WOMAN));
    }

    @Test
    @DisplayName("deleteMember Test")
    void testDeleteMember() {
        //when
        assertDoesNotThrow(() -> memberService.deleteMember(1L));

        //then
        Mockito.verify(memberRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("getMemberById Test")
    void testGetMemberById() {
        //given
        Member expectedMember = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        when(memberRepository.findById(1L)).thenReturn(Optional.of(expectedMember));

        //when
        Member result = memberService.getMemberById(1L);

        //then
        assertEquals(expectedMember, result);
    }

    @Test
    @DisplayName("getMemberById Test When memberNonExists")
    void testGetMemberByIdNonExisting() {
        //given
        when(memberRepository.findById(2L)).thenReturn(Optional.empty());

        //then
        assertThrows(RuntimeException.class, () -> memberService.getMemberById(2L));
    }
}
