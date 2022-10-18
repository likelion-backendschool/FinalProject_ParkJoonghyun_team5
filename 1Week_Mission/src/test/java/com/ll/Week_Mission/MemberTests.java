package com.ll.Week_Mission;

import com.ll.Week_Mission.member.controller.MemberController;
import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.member.form.JoinForm;
import com.ll.Week_Mission.member.repository.MemberRepository;
import com.ll.Week_Mission.member.service.MemberService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MemberTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("Member 회원가입 폼 접근")
    @Test
    public void memberControllerTestShowJoin() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(
                get("/member/join"))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showJoin"));
    }

    @DisplayName("Member 회원가입 확인")
    @Test
    public void memberControllerTestJoin() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(
                        post("/member/join")
                                .param("username","user1")
                                .param("password","password1")
                                .param("passwordConfirm","password1")
                                .param("email","1@google.com")
                                .param("nickname","user1"))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(content().string(containsString("user1")));
    }

    @DisplayName("Member 회원가입 서비스 확인")
    @Test
    public void memberServiceTestJoin() throws Exception {
        String username = "user1";
        String password = "1234";
        String email = "1@gmail.com";
        String passwordConfirm = "1234";
        String nickname = "user1";

        JoinForm joinForm = new JoinForm(username,password,passwordConfirm,email,nickname);

        memberService.join(joinForm);
//        Member member1 = new Member();
//        member1.setEmail(email);
//        member1.setUsername(username);
//        member1.setPassword(password);
//        member1.setNickname(nickname);
//        memberRepository.save(member1);
        Member member = memberService.findByUsername("user1");
        assertThat(member.getUsername()).isEqualTo(username);

    }

}
