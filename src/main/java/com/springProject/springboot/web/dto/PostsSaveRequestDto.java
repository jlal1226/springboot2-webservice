package com.springProject.springboot.web.dto;

import com.springProject.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    /*
    Entity 클래스와 매우 유사하지만 절대로 Entity 클래스를 Request/Response 클래스로 사용하면 안됌
    Entity 클래스 : DB와 맞닿은 핵심 클래스
    Request/Response 용 Dto는 View를 위한 클래스 -> 자주 변경이 필요함
    DB Layer와 View Layer의 역할 분리를 철저하게 해야됌
     */
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

