package com.ll.Week_Mission.post.controller;

import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String main(){
        return "main";
    }


    @GetMapping("/post/list")
    public String listPost(){
        List<Post> postList = postService.getList();
        return "list";
    }

    @GetMapping("/post/write")
    public String getPostForm(){
        return "getPostForm";
    }

    @PostMapping("/post/write")
    public String writePost(){
        PostForm postForm = new PostForm();
        postService.create(postForm);
        return "writePost";
    }

    @GetMapping("/post/{id}")
    public String detailPost(@PathVariable long id){
        Post post = postService.getPost(id);
        return "detail";
    }

    @GetMapping("/post/{id}/modify")
    public String modifyPostByGet(@PathVariable long id){
        return "modify";
    }

    @PostMapping("/post/{id}/modify")
    public String modifyPostByPost(@PathVariable long id){
        return "modify";
    }

    @GetMapping("/post/{id}/delete")
    public String deletePost(@PathVariable long id){
        return "delete";
    }
}