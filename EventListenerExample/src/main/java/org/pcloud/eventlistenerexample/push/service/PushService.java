package org.pcloud.eventlistenerexample.push.service;

import org.pcloud.eventlistenerexample.push.dto.form.PushForm;

public interface PushService {
    void push(PushForm form);
}
