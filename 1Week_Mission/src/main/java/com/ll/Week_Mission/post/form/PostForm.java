package com.ll.Week_Mission.post.form;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class PostForm {

    @NotEmpty(message = "제목을 입력해주세요")
    private String subject;

    @NotEmpty(message = "내용을 입력해주세요")
    private String content;

    private String keywords;

}
