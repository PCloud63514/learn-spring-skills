package org.pcloud.eventlistenerexample.user.controller;

import lombok.RequiredArgsConstructor;
import org.pcloud.eventlistenerexample.user.dto.form.AddUserForm;
import org.pcloud.eventlistenerexample.user.dto.form.JoinUserApiForm;
import org.pcloud.eventlistenerexample.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserApi {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> joinUser(@RequestBody @Valid JoinUserApiForm form) {
        userService.AddUser(new AddUserForm(form.getNickName(), form.getPassword()));

        return ResponseEntity.ok("성공");
    }
}
