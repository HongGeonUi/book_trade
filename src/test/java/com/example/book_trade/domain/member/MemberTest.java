package com.example.book_trade.domain.member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberTest {

    @Test
    public void updateMemberTest() {
        //given
        Member memberToUpdate = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();
        String passwordToUpdate = "bbbBBB222@@@";
        String nameToUpdate = "홍길동";
        Sex sexToUpdate = Sex.WOMAN;

        //when
        memberToUpdate.update(passwordToUpdate, nameToUpdate, sexToUpdate);

        //then
        assertEquals(passwordToUpdate,memberToUpdate.getPassword());
        assertEquals(nameToUpdate, memberToUpdate.getName());
        assertEquals(sexToUpdate, memberToUpdate.getSex());
    }
}
