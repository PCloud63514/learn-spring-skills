package org.pcloud.eventlistenerexample.push.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcloud.eventlistenerexample.push.dto.form.PushForm;
import org.pcloud.eventlistenerexample.push.handler.event.PushEvent;
import org.pcloud.eventlistenerexample.push.service.PushService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PushEventHandler {
    private final PushService pushService;
    @EventListener
    public void push(PushEvent event) {
        log.info("이벤트 수신 완료");
        pushService.push(new PushForm(event.getMsg()));
    }
}
