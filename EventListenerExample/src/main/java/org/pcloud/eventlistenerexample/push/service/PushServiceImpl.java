package org.pcloud.eventlistenerexample.push.service;

import lombok.extern.slf4j.Slf4j;
import org.pcloud.eventlistenerexample.push.dto.form.PushForm;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PushServiceImpl implements PushService {

    @Override
    public void push(PushForm form) {
        log.info("푸쉬 메시지 전송 성공 : {}", form.getMsg());
    }
}
