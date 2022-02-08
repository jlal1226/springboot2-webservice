package com.springProject.springboot.service.posts;

import com.springProject.springboot.domain.posts.Posts;
import com.springProject.springboot.domain.posts.PostsRepository;
import com.springProject.springboot.web.dto.PostsListResponseDto;
import com.springProject.springboot.web.dto.PostsResponseDto;
import com.springProject.springboot.web.dto.PostsSaveRequestDto;
import com.springProject.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // readOnly = true : 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됌
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해
        // PostsListResponseDto 변환 -> List로 반환하는 메소드
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 삭제
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        postsRepository.delete(posts); // JpaRepository 에서 delete 메소드를 지원함
    }
}
