package com.springProject.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // "index"를 반환하면 index.mustache로 전환되어 View Resolver가 처리함
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 호출되면 posts-save.mustache로 전환됌
    @GetMapping("/post/save")
    public String postsSave() {
        return "posts-save";
    }
}
