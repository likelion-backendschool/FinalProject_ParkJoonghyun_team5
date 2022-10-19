package com.ll.Week_Mission.security;

import com.ll.Week_Mission.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
public class MemberContext extends User {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username;
    private final String password;
    private final String email;
    private final String nickname;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getMemberId();
        this.createDate = member.getCreateDate();
        this.modifyDate = member.getUpdateDate();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
    }


}
