package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.EssayCommentDTO;
import com.rc.redacaoconectada.dtos.EssayCommentInsertDTO;
import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.CommentRepository;
import com.rc.redacaoconectada.repositories.EssayRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EssayRepository essayRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public EssayCommentDTO insertComment(EssayCommentInsertDTO comment) {
        Comment commentTemp = new Comment();
        dtoCommentToEntityConverter(comment, commentTemp);
        commentTemp = commentRepository.save(commentTemp);
        return new EssayCommentDTO(commentTemp);
    }

    private void dtoCommentToEntityConverter(EssayCommentInsertDTO commentDTO, Comment comment) {

        var user = userRepository.findById(commentDTO.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var essay = essayRepository.findById(commentDTO.getIdEssay())
                .orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        comment.setBody(commentDTO.getBody());
        comment.setEssay(essay);
        comment.setUser(user);
    }
}
