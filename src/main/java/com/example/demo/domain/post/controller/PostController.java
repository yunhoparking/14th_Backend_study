package com.example.demo.domain.post.controller;

import com.example.demo.domain.post.dto.request.PostCreateRequest;

import com.example.demo.domain.post.dto.response.PostAllResponse;
import com.example.demo.domain.post.dto.response.PostCreateResponse;
import com.example.demo.domain.post.dto.response.PostResponse;
import com.example.demo.domain.post.service.PostService;
import com.example.demo.global.common.dto.SuccessResponse;
import com.example.demo.domain.post.dto.request.PostUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostCreateResponse create(@RequestBody @Valid PostCreateRequest request) {
        return postService.create(request);
    }

    @GetMapping
    public List<PostAllResponse> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public PostResponse findById(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PatchMapping("/{postId}")
    public PostResponse update(@PathVariable Long postId, @RequestBody @Valid PostUpdate request) {
        return postService.update(postId, request);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

//    @GetMapping("/{postId}")
//    public SuccessResponse<PostResponse> getPostById(@PathVariable Long postId) {
//        return SuccessResponse.ok(postService.getPost(postId));
//    }

}
