package com.ll.Week_Mission.post.repository;

import com.ll.Week_Mission.post.entity.Post;
import com.ll.Week_Mission.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ll.Week_Mission.post.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    QPost qPost = post;
    public List<Post> findHundred(){
        return jpaQueryFactory
                .select(post)
                .from(post)
                .orderBy(post.updateDate.desc())
                .limit(100)
                .fetch();
    }
}
