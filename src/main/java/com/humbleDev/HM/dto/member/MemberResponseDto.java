package com.humbleDev.HM.dto.member;

import com.humbleDev.HM.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String email;
    private String pw;
    private String nick;
    private String phone;
    private char isad;

    public MemberResponseDto(Member entity) {
        this.email = entity.getEmail();
        this.pw = entity.getPw();
        this.nick = entity.getNick();
        this.phone = entity.getPhone();
        this.isad = entity.getIsad();
    }
}
