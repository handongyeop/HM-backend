package com.humbleDev.HM.controller;

import com.humbleDev.HM.dto.member.MemberSaveRequestDto;
import com.humbleDev.HM.dto.member.MemberUpdateRequestDto;
import com.humbleDev.HM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<?> save(@RequestBody MemberSaveRequestDto requestDto) {
        return new ResponseEntity<>(memberService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/member")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return new ResponseEntity<>(memberService.update(id, requestDto), HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<?> findByID(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/member/login/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return new ResponseEntity<>(memberService.findByEmail(email), HttpStatus.OK);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.deleteById(id), HttpStatus.OK);
    }



}
