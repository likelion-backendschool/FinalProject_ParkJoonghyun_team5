package com.ll.Week_Mission.post.service;

import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public void create(PostForm postForm){
        Post post = new Post();
        post.setSubject(postForm.getSubject());
        post.setContent(postForm.getContent());
        post.setCreateDate(LocalDateTime.now());
        post.setUpdateDate(LocalDateTime.now());
        post.setAuthorId(2l);
        postRepository.save(post);
    }

    @Override
    public List<Post> getList(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

}
