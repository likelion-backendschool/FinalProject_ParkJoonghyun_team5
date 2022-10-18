package com.ll.Week_Mission.member.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPasswordForm {
    @NotEmpty
    private String oldPassword;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConfirm;
}
