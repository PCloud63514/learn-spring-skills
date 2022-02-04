package org.pcloud.eventlistenerexample.push.dto.form;

import lombok.Getter;

@Getter
public class PushForm {
    private final String msg;

    private PushForm(String msg) {
        this.msg = msg;
    }
}
