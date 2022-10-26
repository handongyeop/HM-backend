package com.humbleDev.HM.controller;

import com.humbleDev.HM.domain.member.Member;
import com.humbleDev.HM.dto.member.MemberSaveRequestDto;
import com.humbleDev.HM.dto.member.MemberUpdateRequestDto;
import com.humbleDev.HM.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void tearDown() throws Exception {
        memberRepository.deleteAll();
    }

    @Test
    public void insertMember() throws Exception {
        String email = "pig8776@maver.com";
        String pw = "gkgkgk";
        String nick = "엽겹이";
        String phone = "010-9923-8776";
        char isad = 'Y';

        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder().email(email).pw(pw).nick(nick).phone(phone).isad(isad).build();

        String url = "http://localhost:" + port + "/member";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> all = memberRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getPw()).isEqualTo(pw);
        assertThat(all.get(0).getNick()).isEqualTo(nick);
        assertThat(all.get(0).getPhone()).isEqualTo(phone);
        assertThat(all.get(0).getIsad()).isEqualTo(isad);
    }

    @Test
    public void updateMember() throws Exception {
        Member savedMember = memberRepository.save(Member.builder().email("test@naver.com").pw("qlalsqjsgh").nick("별명").phone("010-1234-5678").isad('Y').build());

        Long updateId = savedMember.getId();
        String expectedPw = "qlalfqjsgh2";
        String expectedNick = "별명2";
        String expectedPhone = "010-5678-1234";
        char expectedIsad = 'N';

        MemberUpdateRequestDto requestDto = MemberUpdateRequestDto.builder().pw(expectedPw).nick(expectedNick).phone(expectedPhone).isad(expectedIsad).build();

        String url = "http://localhost:" + port + "/member/" + updateId;

        HttpEntity<MemberUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> all = memberRepository.findAll();
        assertThat(all.get(0).getPw()).isEqualTo(expectedPw);
        assertThat(all.get(0).getNick()).isEqualTo(expectedNick);
        assertThat(all.get(0).getPhone()).isEqualTo(expectedPhone);
        assertThat(all.get(0).getIsad()).isEqualTo(expectedIsad);

    }
}
