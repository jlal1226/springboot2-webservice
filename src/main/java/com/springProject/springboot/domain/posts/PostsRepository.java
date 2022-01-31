package com.springProject.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { }
/*
1. Dao라 불리는 DB Layer 접근자
2. 기본적인 CRUD 메소드가 자동으로 생성된다.
3. @Repository 필요 x
4. Entity 클래스와 Entity Repository는 항상 같은 위치에 있어야 한다. -> domain 패키지에서 함께 관리
 */
