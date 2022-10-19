package com.ll.Week_Mission.member.controller;

import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.member.form.JoinForm;
import com.ll.Week_Mission.member.form.LoginForm;
import com.ll.Week_Mission.member.form.ModifyForm;
import com.ll.Week_Mission.member.form.ModifyPasswordForm;
import com.ll.Week_Mission.member.service.MemberService;
import com.ll.Week_Mission.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String showJoin(){
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm){
        memberService.join(joinForm);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "member/login";
    }

    @GetMapping("/modify")
    public String showModifyForm(){
        return "modify";
    }

    @PostMapping("/modify")
    public String modifyMemberInfo(@AuthenticationPrincipal MemberContext memberContext, @Valid ModifyForm modifyForm){
        Member member = memberService.getMemberById(memberContext.getId());

        memberService.modify(member, modifyForm);

        return modifyForm.getNickname()+"으로 변경되었습니다.";
    }

    @GetMapping("/modifyPassword")
    public String modifyPasswordShow(){
        return "modifyPassword";
    }

    @PostMapping("/modifyPassword")
    public String modifyPassword(@AuthenticationPrincipal MemberContext memberContext, @Valid ModifyPasswordForm modifyPasswordForm){
        Member member = memberService.getMemberById(memberContext.getId());

        memberService.modifyPassword(member, modifyPasswordForm);

        return modifyPasswordForm.getPassword()+"으로 변경되었습니다.";
    }

}
