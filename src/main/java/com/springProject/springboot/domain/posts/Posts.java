package com.springProject.springboot.domain.posts;

import com.springProject.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스 -> Entity Class
    // JPA를 사용하면 DB의 데이터에 작업할 경우 실제 쿼리를 날리기보단, Entity 클래스를 수정함
    // 클래스의 카멜케이스 이름 -> 언더스토러 네이밍으로 테이블 이름을 매치
    // ex) SalesManager.java -> sales_manager table

    // Entity 클래스에 setter 메소드를 절대 만들지 말것.
    // 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가할것

    // Entity의 PK -> Long 타입의 Auto_increment를 추천
    // 주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는 것을 추천 -> PK로 사용 x
    @Id // 해당 테이블의 primary key를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    private Long id;

    // 테이블의 칼럼을 나타낸다 -> 기본적으로 모든 필드는 칼럼이 됌
    @Column(length = 500, nullable = false) // -> but 변경사항이 있는 경우 적는다
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
    빌더 패턴 -> ex) StringBuilder
    1. 어느 필드에 어떤 값을 채워야 할지 명확해짐
     */
    @Builder // 생성자 대신 빌더 클래스
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /*
        DB에 쿼리를 날리는 부분이 없음 -> JPA의 영속성 컨텍스트 때문
        영속성 컨텍스트 : entity를 영구적으로 저장하는 환경 (논리적 개념)
        영속성 컨텍스트가 유지된 상태에서 데이터 값을 변경하면
        transaction이 끝나는 시점에 해당 테이블에 변경문이 반영된다. -> "터티 체킹"
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
