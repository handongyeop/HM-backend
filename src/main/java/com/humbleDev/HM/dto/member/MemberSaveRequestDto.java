package com.humbleDev.HM.dto.member;

import com.humbleDev.HM.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String email;
    private String pw;
    private String nick;
    private String phone;
    private char isad;

    @Builder
    public MemberSaveRequestDto(String email, String pw, String nick, String phone, char issvc, char isad) {
        this.email = email;
        this.pw = pw;
        this.nick = nick;
        this.phone = phone;
        this.isad = isad;
    }

    public Member toEntity() {
        return Member.builder().email(email).pw(pw).nick(nick).phone(phone).isad(isad).build();
    }
}
