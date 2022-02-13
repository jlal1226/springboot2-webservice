package com.springProject.springboot.config.auth.dto;

import com.springProject.springboot.domain.user.User;
import lombok.Getter;

// 세션에 사용자 정보를 저장하기 위한 Dto 클래스
// 인증된 사용자 정보만 필요로함
@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
/*
왜 User 클래스를 안쓸까?
1. User 클래스를 세션에 저장하려면 User 클래스에 직렬화를 구현해야됌
2. User 클래스는 Entity이기 때문에 @OneToMany, @ManyToMany 등 자식 Entity를 가지고 있으면 직렬화 대상에 자식들도 포함됌
3. 성능 이슈, 부수 효과가 발생할 확률이 높음
4. 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 된다.
 */
