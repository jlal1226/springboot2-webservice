package com.springProject.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication annotation을 통해 스프링 부트의 자동 설정, 스프링 Bean 일기와 생성을
// 모두 자동으로 설정
// 항상 프로젝트 최상단에 있어야함
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS 실행 -> application을 실행할 때 내부에서 WAS를 실행
        // 스프링 부트로 만들어진 Jar파일로 실행
        // 언제 어디서나 같은 환경에서 스프링 부트를 배포 가능
        SpringApplication.run(Application.class, args);
    }
}
