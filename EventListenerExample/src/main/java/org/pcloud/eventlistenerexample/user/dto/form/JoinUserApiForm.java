package org.pcloud.eventlistenerexample.user.dto.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JoinUserApiForm {
    @NotBlank(message = "닉네임이 입력되지 않았습니다.")
    private final String nickName;
    @NotBlank(message = "패스워드가 입력되지 않았습니다.")
    private final String password;

    public JoinUserApiForm(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
}
