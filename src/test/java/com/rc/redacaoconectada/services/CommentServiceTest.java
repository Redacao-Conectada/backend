package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.factory.CommentFactory;
import com.rc.redacaoconectada.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CommentServiceTests {

    @InjectMocks
    private CommentService service;

    @Mock
    private CommentRepository repository;

    private Comment existingComment;

    @BeforeEach
    void setUp() throws Exception {
        existingComment = CommentFactory.createComment();
        when(repository.findById(existingComment.getId())).thenReturn(Optional.of(existingComment));
    }

}
