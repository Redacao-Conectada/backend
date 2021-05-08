package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import com.rc.redacaoconectada.factory.ChangeRoleRequestFactory;
import com.rc.redacaoconectada.repositories.ChangeRoleRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ChangeRoleServiceTests {

    @InjectMocks
    private ChangeRoleService service;

    @Mock
    private ChangeRoleRequestRepository repository;

    private ChangeRoleRequest existingChangeRoleRequest;

    @BeforeEach
    void setUp() throws Exception {
        existingChangeRoleRequest = ChangeRoleRequestFactory.createChangeRoleRequest();

        when(repository.findById(existingChangeRoleRequest.getId())).thenReturn(Optional.of(existingChangeRoleRequest));
    }

}