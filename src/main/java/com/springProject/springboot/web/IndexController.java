package com.springProject.springboot.web;

import com.springProject.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    // "index"를 반환하면 index.mustache로 전환되어 View Resolver가 처리함
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    // 호출되면 posts-save.mustache로 전환됌
    @GetMapping("/post/save")
    public String postsSave() {
        return "posts-save";
    }
}
