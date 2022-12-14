package com.ll.Week_Mission.post.controller;

import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.service.PostService;
import com.ll.Week_Mission.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String main(Model model){
        List<Post> postList = postService.getHundredList();
        model.addAttribute("postList",postList);
        return "home/main";
    }


    @GetMapping("/post/list")
    public String listPost(Model model){
        List<Post> postList = postService.getList();
        model.addAttribute("postList",postList);
        return "post/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/write")
    public String getPostForm(){
        return "post/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/write")
    public String writePost(@Valid PostForm postForm, @AuthenticationPrincipal MemberContext memberContext){
        postService.create(postForm, memberContext.getId());
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detailPost(@PathVariable long id, Model model){
        Post post = postService.getPost(id);

        model.addAttribute("post",post);
        return "post/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{id}/modify")
    public String showModify(@AuthenticationPrincipal MemberContext memberContext, @PathVariable long id, Model model){
        Post post = postService.getAuthorArticleById(id);

        if (memberContext.memberIsNot(post.getAuthor())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        model.addAttribute("post",post);

        return "post/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{id}/modify")
    public String modifyPostByPost(@AuthenticationPrincipal MemberContext memberContext, @PathVariable long id, @Valid PostForm postForm){
        Post post = postService.getAuthorArticleById(id);

        if (memberContext.memberIsNot(post.getAuthor())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        postService.modifyPost(postForm, memberContext.getId(), id);

        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{id}/delete")
    public String deletePost(@AuthenticationPrincipal MemberContext memberContext, @PathVariable long id){
        Post post = postService.getAuthorArticleById(id);

        if(memberContext.memberIsNot(post.getAuthor())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        postService.deletePost(post);

        return "redirect:/";
    }
}