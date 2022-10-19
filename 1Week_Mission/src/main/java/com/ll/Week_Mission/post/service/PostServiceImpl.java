package com.ll.Week_Mission.post.service;

import com.ll.Week_Mission.DataNotFoundException;
import com.ll.Week_Mission.member.entity.Member;
import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.form.PostForm;
import com.ll.Week_Mission.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public void create(PostForm postForm, Long memberContextId){
        create(new Member(memberContextId), postForm.getSubject(), postForm.getContent());
    }

    public void create(Member author, String subject, String content){
        Post post = Post.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .contentHtml(content)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        postRepository.save(post);

    }

    @Override
    public List<Post> getList(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    @Override
    public List<Post> getHundredList() {
        List<Post> postList = postRepository.findHundred();
        return postList;
    }

    @Override
    public Post getPost(long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            return post.get();
        }
        else{
            throw new DataNotFoundException("post Not Found");
        }
//        return postRepository.findById(id).orElseThrow(() -> new DataNotFoundException("post not found"));
    }

    @Override
    public void modifyPost(PostForm postForm, Long memberContextId, Long id){
        modify(new Member(memberContextId), postForm.getSubject(), postForm.getContent(), id);
    }

    public void modify(Member author, String subject, String content, Long id){
        Post post = getPost(id);
        post.setUpdateDate(LocalDateTime.now());
        post.setSubject(subject);
        post.setContent(content);

        postRepository.save(post);

    }

    // 삭제 서비스 코드
    @Override
    public void deletePost(Post post){
        postRepository.delete(post);
    }

    // 해당 글의 작가가 쓴 글인지 확인하기 위한 서비스 코드
    @Override
    public Post getAuthorArticleById(long id) {
        Post post = getPost(id);

        return post;
    }

}
