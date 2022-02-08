package com.springProject.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
1. Dao라 불리는 DB Layer 접근자
2. 기본적인 CRUD 메소드가 자동으로 생성된다.
3. @Repository 필요 x
4. Entity 클래스와 Entity Repository는 항상 같은 위치에 있어야 한다. -> domain 패키지에서 함께 관리
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성해도 됌
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    // SpringDataJpa 활용하여 등록/수정/삭제
    // Querydsl 프레임워크를 활용하여 조회 -> 타입 안정성 보장, 많은 회사에서 사용중
}
