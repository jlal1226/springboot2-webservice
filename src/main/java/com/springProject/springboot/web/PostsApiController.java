package com.springProject.springboot.web;

import com.springProject.springboot.service.posts.PostsService;
import com.springProject.springboot.web.dto.PostsResponseDto;
import com.springProject.springboot.web.dto.PostsSaveRequestDto;
import com.springProject.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 의존성 주입을 편의성을 위해 사용됌 -> final or NonNull 필드에 대해 생성자를 생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // REST 규약을 따름
    // 등록 -> POST
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // 수정 -> PUT
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 조회 -> GET
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
