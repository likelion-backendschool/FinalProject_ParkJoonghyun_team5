package com.ll.Week_Mission;

import com.ll.Week_Mission.post.controller.PostController;
import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PostTests {
    @Autowired
    PostController postController;

    @Autowired
    PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("post controller 테스트")
    @Test
    public void postTestController() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("main"));
    }

    @DisplayName("글 생성 테스트1")
    @Test
    public void postCreateTest() throws Exception {
        PostForm postForm = new PostForm();
        postForm.setContent("hello everyone");
        postForm.setKeywords("restaurant");
        postForm.setSubject("hello");

        postService.create(postForm);
    }

    @DisplayName("글 리스트 확인")
    @Test
    public void postListTest() throws Exception{
        List<Post> postList = postService.getList();
        assertThat(postList.size()).isEqualTo(1);
    }
}
