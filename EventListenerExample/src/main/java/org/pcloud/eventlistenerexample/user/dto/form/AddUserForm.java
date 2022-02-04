package org.pcloud.eventlistenerexample.user.dto.form;

import lombok.Getter;

@Getter
public class AddUserForm {
    private final String nickName;
    private final String password;

    public AddUserForm(String nickName, String password) {
        if (nickName == null || nickName.isBlank()) {
            throw new IllegalArgumentException("닉네임이 입력되지 않았습니다.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("패스워드가 입력되지 않았습니다.");
        }

        this.nickName = nickName;
        this.password = password;
    }
}
