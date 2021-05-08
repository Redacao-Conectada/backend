package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class ChangeRoleRequestFactory {

    public static ChangeRoleRequest createChangeRoleRequest() {
        User user = UserFactory.createUser();
        return new ChangeRoleRequest(1L, "TURURURU", "Paraibaja", "FakeLink666", user) ;
    }
}