package com.springProject.springboot.web;

import com.springProject.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// WebMvcTest는 스캔대상이 따로 정해져 있음 -> CustomOAuth2UserService 스캔 못함
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = SecurityConfig.class)
        }) // JPA 작동안함
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean 주입
   private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDot가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) // 요청 파라미터 설정, String만 가능
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect((jsonPath("$.amount", is(amount))));
        // jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $를 기준으로 필드명 명시
        // $.name과 $.amount로 검증
    }
}
