package com.ll.Week_Mission.member.service;

import com.ll.Week_Mission.DataNotFoundException;
import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.member.exception.AlreadyJoinException;
import com.ll.Week_Mission.member.form.JoinForm;
import com.ll.Week_Mission.member.form.ModifyForm;
import com.ll.Week_Mission.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    // JoinForm을 가져와서
    @Override
    public void join(JoinForm joinForm){
        if (memberRepository.existsByUsername(joinForm.getUsername())){
            throw new AlreadyJoinException("user 아이디가 중복됩니다.");
        }
        else if (memberRepository.existsByEmail(joinForm.getEmail())){
            throw new AlreadyJoinException("이메일이 중복됩니다.");
        }
        else if (memberRepository.existsByNickname(joinForm.getNickname())){
            throw new AlreadyJoinException("닉네임이 중복됩니다.");
        }

        Member member = Member.builder()
            .username(joinForm.getUsername())
            .password(passwordEncoder.encode(joinForm.getPassword()))
            .email(joinForm.getEmail())
            .nickname(joinForm.getNickname())
            .createDate(LocalDateTime.now())
            .build();

        memberRepository.save(member);
    }

    @Override
    public Member findByUsername(String username){
        return this.memberRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException("member not found"));
    }

    @Override
    public Member getMemberId(Long id){
        return this.memberRepository.findById(id).orElseThrow(() -> new DataNotFoundException("member not found"));
    }

    @Override
    public void modify(ModifyForm modifyForm){

    }
}
