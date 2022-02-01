package com.springProject.springboot.service.posts;

import com.springProject.springboot.domain.posts.Posts;
import com.springProject.springboot.domain.posts.PostsRepository;
import com.springProject.springboot.web.dto.PostsResponseDto;
import com.springProject.springboot.web.dto.PostsSaveRequestDto;
import com.springProject.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional // 데이터베이스의 상태를 변경시키는 작업
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId(); // save() : 테이블 posts에 insert/update 쿼리를 실행
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));

        return new PostsResponseDto(entity);
    }
}
