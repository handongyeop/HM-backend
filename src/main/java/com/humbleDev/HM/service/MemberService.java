package com.humbleDev.HM.service;

import com.humbleDev.HM.domain.member.Member;
import com.humbleDev.HM.dto.member.MemberResponseDto;
import com.humbleDev.HM.dto.member.MemberSaveRequestDto;
import com.humbleDev.HM.dto.member.MemberUpdateRequestDto;
import com.humbleDev.HM.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto requestDto) {
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        member.update(requestDto.getPw(), requestDto.getNick(), requestDto.getPhone(), requestDto.getIsad());
        return id;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        return new MemberResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByEmail(String email) {
        Member entity = Optional
                .ofNullable(memberRepository.findByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        return new MemberResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public String deleteById(Long id) {
        memberRepository.deleteById(id);
        return "해당 회원을 삭제하였습니다.";
    }
}
