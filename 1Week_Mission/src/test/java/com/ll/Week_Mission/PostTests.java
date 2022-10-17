package com.ll.Week_Mission;

import com.ll.Week_Mission.post.controller.PostController;
import com.ll.Week_Mission.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    public void postCreateTest(){

    }
}
