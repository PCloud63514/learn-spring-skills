package org.pcloud.eventlistenerexample.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcloud.eventlistenerexample.push.handler.event.PushEvent;
import org.pcloud.eventlistenerexample.user.domain.User;
import org.pcloud.eventlistenerexample.user.dto.form.AddUserForm;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UUID AddUser(AddUserForm form) {
        log.info("AddUser 요청");
        User user = User.create(form.getNickName(), form.getPassword());
        // repository save가
        log.info("User 생성 완료!");
        eventPublisher.publishEvent(new PushEvent(form.getNickName() + "님의 가입을 축하합니다!"));
        return user.getId();
    }
}
