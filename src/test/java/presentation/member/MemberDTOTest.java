package presentation.member;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.Sex;
import com.example.book_trade.presentation.member.dto.MemberDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberDTOTest {

    @Test
    @DisplayName("fromEntity Test")
    void testFromEntity() {
        //given
        Member member = Member.builder()
                .id(1L)
                .email("keaam12@gmail.com")
                .password("aaaAAA111!!!")
                .name("홍건의")
                .sex(Sex.MAN)
                .build();

        //when
        MemberDTO memberDTO = MemberDTO.fromEntity(member);

        //then
        assertEquals("keaam12@gmail.com", memberDTO.getEmail());
        assertEquals("aaaAAA111!!!", memberDTO.getPassword());
        assertEquals("홍건의", memberDTO.getName());
        assertEquals(Sex.MAN, memberDTO.getSex());
    }
}

