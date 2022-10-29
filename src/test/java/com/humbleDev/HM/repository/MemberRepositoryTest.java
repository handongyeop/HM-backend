package com.humbleDev.HM.repository;

import com.humbleDev.HM.domain.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void saveMember() {
        String email = "test@naver.com";
        String pw = "qlalfqjsgh";
        String nick = "별명";
        String phone = "010-1234-5678";
        char isad = 'Y';

        memberRepository.save(Member.builder().email(email).pw(pw).nick(nick).phone(phone).isad(isad).build());
    }

    @Test
    public void insertBaseTileEntity() {
        LocalDateTime now = LocalDateTime.now();
        memberRepository.save(Member.builder().email("test@naver.com").pw("qlalsqjsgh").nick("별명").phone("010-1234-5678").isad('Y').build());

        List<Member> memberList = memberRepository.findAll();
        Member member = memberList.get(0);

        System.out.println(">>>>>>>>> createDate = " + member.getCreatedDate() + ", modifiedDate = " + member.getModifiedDate());

        assertThat(member.getCreatedDate()).isAfter(now);
        assertThat(member.getModifiedDate()).isAfter(now);
    }

    @Test
    void findByEmail() {
        String email = "test@naver.com";
        String pw = "qlalfqjsgh";
        String nick = "별명";
        String phone = "010-1234-5678";
        char isad = 'Y';

        memberRepository.save(Member.builder().email(email).pw(pw).nick(nick).phone(phone).isad(isad).build());
        Member user = memberRepository.findByEmail("test@naver.com");
        System.out.println("번호 : " + user.getId() + ", 이메일 : " + user.getEmail()); ;
    }

}
