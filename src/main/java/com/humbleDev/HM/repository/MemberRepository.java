package com.humbleDev.HM.repository;

import com.humbleDev.HM.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
