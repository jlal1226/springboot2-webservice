package com.springProject.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식함
@EntityListeners(AuditingEntityListener.class) // 클래스에 Auditing 기능을 포함시킴
public class BaseTimeEntity {
    // 해당 클래스를 상속 받으면 자동으로 등록일 / 수정일을 저장할 수 있음
    @CreatedDate
    private LocalDateTime createdDate; // Entity가 생성되어 저장될 때, 시간이 자동으로 저장됌

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 조회한 Entity의 값을 변경할 때, 시간이 자동으로 저장됌됌
}
