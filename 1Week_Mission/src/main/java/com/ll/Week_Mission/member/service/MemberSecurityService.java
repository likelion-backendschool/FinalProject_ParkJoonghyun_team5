package com.ll.Week_Mission.member.service;

import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.member.repository.MemberRepository;
import com.ll.Week_Mission.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new MemberContext(member,authorities);
    }
}
