package com.ll.Week_Mission.post.service;

import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    public void create(PostForm postForm);
}
