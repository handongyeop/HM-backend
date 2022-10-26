package com.humbleDev.HM.domain.member;

import com.humbleDev.HM.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "t_member_info")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터 베이스 번호증가 전략 따름
    @Column(name = "mi_id")
    private Long id;
    @Column(name = "mi_email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "mi_pw", nullable = false, length = 20)
    private String pw;
    @Column(name = "mi_nick", nullable = false, length = 20)
    private String nick;
    @Column(name = "mi_phone", nullable = false, length = 13)
    private String phone;
    @Column(name = "mi_isad", nullable = false)
    private char isad;


    @Builder
    public Member(String email, String pw, String nick, String phone, char issvc, char isad) {
        this.email = email;
        this.pw = pw;
        this.nick = nick;
        this.phone = phone;
        this.isad = isad;
    }

    public void update(String pw, String nick, String phone, char isad) {
        this.pw = pw;
        this.nick = nick;
        this.phone = phone;
        this.isad = isad;
    }
}
