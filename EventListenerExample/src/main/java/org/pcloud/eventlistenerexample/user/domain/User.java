package org.pcloud.eventlistenerexample.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User {
    private UUID id;
    private String nickName;
    private String password;

    @Builder
    private User(UUID id, String nickName, String password) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
    }

    public static User create(String nickName, String password) {
        return User.builder()
                .id(UUID.randomUUID())
                .nickName(nickName)
                .password(password)
                .build();
    }
}
