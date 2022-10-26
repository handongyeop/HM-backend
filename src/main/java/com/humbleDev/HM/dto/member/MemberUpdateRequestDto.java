package com.humbleDev.HM.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String pw;
    private String nick;
    private String phone;
    private char isad;

    @Builder
    public MemberUpdateRequestDto(String pw, String nick, String phone, char isad) {
        this.pw = pw;
        this.nick = nick;
        this.phone = phone;
        this.isad = isad;
    }
}
