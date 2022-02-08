package com.springProject.springboot.web;

import com.springProject.springboot.service.posts.PostsService;
import com.springProject.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    // "index"를 반환하면 index.mustache로 전환되어 View Resolver가 처리함
    @GetMapping("/")
    public String index(Model model) { // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다
        model.addAttribute("posts", postsService.findAllDesc()); // 결과를 posts로 index.mutache에 전달 -> posts라는 List
        return "index";
    }

    // 호출되면 posts-save.mustache로 전환됌
    @GetMapping("/post/save")
    public String postsSave() {
        return "posts-save";
    }

    // 호출되면 posts-update.mustache로 전환됌
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
