package com.example.book_trade.presentation.member;

import com.example.book_trade.application.member.MemberService;
import com.example.book_trade.presentation.member.dto.MemberDTO;
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

    @PostMapping("/api/member")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberDTO req) {
        memberService.signup(req.getEmail(), req.getPassword(), req.getName(), req.getSex());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/api/member")
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberDTO req) {
        memberService.updateMember(req.getEmail(), req.getPassword(), req.getName(), req.getSex());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/api/member/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/api/member/{id")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MemberDTO.fromEntity(memberService.getMemberById(id)));
    }
}
