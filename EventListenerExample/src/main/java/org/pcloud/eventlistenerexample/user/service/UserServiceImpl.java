package org.pcloud.eventlistenerexample.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationPushBuilder;
import org.pcloud.eventlistenerexample.user.domain.User;
import org.pcloud.eventlistenerexample.user.dto.form.AddUserForm;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ApplicationPushBuilder pushBuilder;

    @Override
    public UUID AddUser(AddUserForm form) {
        User user = User.create(form.getNickName(), form.getPassword());
        // repository save
        return user.getId();
    }
}
