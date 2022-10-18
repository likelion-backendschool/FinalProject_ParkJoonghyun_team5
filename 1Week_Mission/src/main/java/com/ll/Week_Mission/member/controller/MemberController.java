package com.ll.Week_Mission.member.controller;

import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.member.form.JoinForm;
import com.ll.Week_Mission.member.form.ModifyForm;
import com.ll.Week_Mission.member.service.MemberService;
import com.ll.Week_Mission.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String showJoin(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm){
        memberService.join(joinForm);

        return joinForm.getNickname()+"님 가입이 완료되었습니다.";
    }

    @GetMapping("/login")
    public String showLogin(HttpServletRequest request){

        return "login";
    }

    @GetMapping("/modify")
    public String showModifyForm(){
        return "modify";
    }

    @PostMapping("/modify")
    public String modifyMemberInfo(@Valid ModifyForm modifyForm){
        memberService.modify(modifyForm);

        return modifyForm.getNickname()+"으로 변경되었습니다."
    }

}
