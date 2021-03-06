package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.factory.EssayFactory;
import com.rc.redacaoconectada.factory.UserFactory;
import com.rc.redacaoconectada.repositories.EssayRepository;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EssayServiceTests {

    @InjectMocks
    private EssayService service;

    @Mock
    private EssayRepository repository;

    private Essay existingEssay;

    private User existingUser;

    @BeforeEach
    void setUp() throws Exception {
        existingEssay = EssayFactory.createEssay();

        existingUser = UserFactory.createUser();

        when(repository.findById(existingEssay.getId())).thenReturn(Optional.of(existingEssay));
    }
}
