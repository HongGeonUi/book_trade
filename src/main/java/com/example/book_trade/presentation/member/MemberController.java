package com.example.book_trade.presentation.member;

import com.example.book_trade.application.member.MemberService;
import com.example.book_trade.presentation.member.dto.MemberDTO;
import com.example.book_trade.presentation.member.dto.SignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "멤버 등록 api", description = "멤버 정보를 등록한다")
    @PostMapping("/api/member")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
        memberService.signup(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "멤버 정보 변경 api", description = "멤버 정보를 변경한다.")
    @PutMapping("/api/member")
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberDTO req) {
        memberService.updateMember(req.getEmail(), req.getPassword(), req.getName(), req.getSex());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "멤버 삭제 api", description = "멤버를 삭제한다.")
    @DeleteMapping("/api/member/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Id로 멤버 검색 api", description = "Id를 이용해 멤버를 검색한다")
    @GetMapping("/api/member/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MemberDTO.fromEntity(memberService.getMemberById(id)));
    }
}
