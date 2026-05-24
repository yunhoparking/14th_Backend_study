package com.example.demo.domain.post.service;

import com.example.demo.domain.post.dto.request.PostCreateRequest;
import com.example.demo.domain.post.dto.request.PostUpdate;
import com.example.demo.domain.post.dto.response.PostAllResponse;
import com.example.demo.domain.post.dto.response.PostCreateResponse;
import com.example.demo.domain.post.dto.response.PostResponse;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.repository.PostRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostCreateResponse create(PostCreateRequest request){
        if(postRepository.existsByTitle(request.title())){
            throw new IllegalArgumentException("이미 존재하는 게시물입니다");
        }
        Post post = Post.create(
                request.title(),
                request.content(),
                request.author()
        );

        postRepository.save(post);
        return PostCreateResponse.of(post);
    }
    //삭제
    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
        postRepository.delete(post);

    }
    public PostResponse getPost(Long postid){

        Post post = postRepository.findById(postid)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(post);
    }
    public List<PostAllResponse> findAll() {
        return postRepository.findAll()
                .stream()
                .map(PostAllResponse::from)
                .toList();
    }
    public PostResponse update(Long postId, PostUpdate request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        post.update(request.title(), request.content());
        return PostResponse.from(post);
    }

}




