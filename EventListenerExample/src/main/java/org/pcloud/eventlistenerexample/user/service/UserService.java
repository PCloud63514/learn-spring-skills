package org.pcloud.eventlistenerexample.user.service;

import org.pcloud.eventlistenerexample.user.dto.form.AddUserForm;

import java.util.UUID;

public interface UserService {
    UUID AddUser(AddUserForm form);
}
