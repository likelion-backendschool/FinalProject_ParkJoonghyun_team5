package com.ll.Week_Mission.member.service;

import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.member.form.JoinForm;
import com.ll.Week_Mission.member.form.ModifyForm;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public void join(JoinForm joinForm);

    public Member findByUsername(String username);

    public void modify(Member member, ModifyForm modifyForm);

    public Member getMemberById(Long id);
}
