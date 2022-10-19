package com.ll.Week_Mission.post.service;

import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.repository.PostRepository;
import com.ll.Week_Mission.security.MemberContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    // 글 등록 서비스
    public void create(PostForm postForm, Long memberContextId);

    // 글 리스트 가져오기 서비스
    public List<Post> getList();

    // 아이디에 해당하는 글 가져오기 서비스
    public Post getPost(long id);

    // 글 수정 서비스
    public void modifyPost(PostForm postForm, Long memberContextId, Long id);

    // 글 삭제 서비스
    public void deletePost(Post post);

    public Post getAuthorArticleById(long id);
}
