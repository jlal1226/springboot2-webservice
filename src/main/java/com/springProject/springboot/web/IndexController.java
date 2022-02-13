package com.springProject.springboot.web;

import com.springProject.springboot.config.auth.LoginUser;
import com.springProject.springboot.config.auth.dto.SessionUser;
import com.springProject.springboot.service.posts.PostsService;
import com.springProject.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    // private final HttpSession httpSession;

    // "index"를 반환하면 index.mustache로 전환되어 View Resolver가 처리함
    // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 어느 컨트롤러든 @LoginUser를 사용하면 세션 정보를 가져올 수 있음
        model.addAttribute("posts", postsService.findAllDesc()); // 결과를 posts로 index.mutache에 전달 -> posts라는 List

        /*
        // CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser를 저장
        SessionUser user = (SessionUser)httpSession.getAttribute("user"); -> 기존의 코드를 annotation 기반으로 개선함
         */

        // 세션에 저장된 값이 있을 때만 model에 userName을 등록
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
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
