package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.EssayCommentDTO;
import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.dtos.EssayInsertDTO;
import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.CommentRepository;
import com.rc.redacaoconectada.repositories.EssayRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.DatabaseException;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EssayService {

    @Autowired
    private EssayRepository essayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<EssayDTO> findAll(PageRequest pageRequest) {

        Page<Essay> essaysBD = this.essayRepository.findAll(pageRequest);

        return essaysBD.map(EssayDTO::new);
    }

    @Transactional(readOnly = true)
    public EssayDTO findEssayById(Long id) {

        Optional<Essay> essayBD = this.essayRepository.findById(id);

        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        return new EssayDTO(essay);
    }

    @Transactional(readOnly = true)
    public List<EssayDTO> findUserEssaysById(Long id) {
        log.info("method=findUserEssaysById, msg=find user id {} essays", id);
        Optional<User> obj = userRepository.findById(id);
        User user = obj.orElseThrow(() -> new ResourceNotFoundException("User not found, id: " + id));
        List<Essay> essays = essayRepository.find(user);

        return essays.stream().map(EssayDTO::new).collect(Collectors.toList());
    }

    public void deleteEssayById(Long id) {
        try {
            Optional<Essay> essayBD = this.essayRepository.findById(id);
            Essay essay = essayBD.get();

            if (essay.getCorrection() != null) {
                throw new ResourceNotFoundException("Delete is not possible, Essay in correction");
            }

            essayRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Essay not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @Transactional
    public EssayDTO insert(EssayInsertDTO essayInsertDTO) {

        Essay essay = mapsEssayInsertDTOtoEssay(essayInsertDTO);

        essay = this.essayRepository.save(essay);

        return new EssayDTO(essay);
    }

    private Essay mapsEssayInsertDTOtoEssay(EssayInsertDTO essayInsertDTO){

        Essay essay = new Essay();

        Optional<User> userBD = userRepository.findById(essayInsertDTO.getIdUser());

        User user = userBD.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        essay.setUser(user);
        essay.setUpVote(0);
        essay.setTitle(essayInsertDTO.getTitle());
        essay.setBody(essayInsertDTO.getBody());

        return essay;
    }

    @Transactional
    public void upVoteEssay(Long id) {

        User user = authService.authenticated();

        Optional<Essay> essayBD = this.essayRepository.findById(id);
        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        if (!essay.getUserUpVotes().contains(user)) {
            essay.setUserUpVotes(user);
            essay.setUpVote(essay.getUpVote() + 1);
        } else {
            throw new ResourceNotFoundException("User has already given a UpVote");
        }

    }

    @Transactional
    public void downVoteEssay(Long id) {

        User user = authService.authenticated();

        Optional<Essay> essayBD = this.essayRepository.findById(id);
        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        if (essay.getUserUpVotes().contains(user)) {
            essay.removeUserUpVotes(user);
            essay.setUpVote(essay.getUpVote() - 1);
        } else {
            throw new ResourceNotFoundException("User did not cast a UpVote");
        }
    }

    @Transactional
    public EssayDTO update(Long id, EssayInsertDTO essayInsertDTO) {
            Optional<Essay> essayBD = this.essayRepository.findById(id);
            Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

            if (essay.getCorrection() != null) {
                throw new ResourceNotFoundException("Update is not possible, Essay in correction");
            }

            Optional<User> userBD = userRepository.findById(essayInsertDTO.getIdUser());
            User user = userBD.orElseThrow(() -> new ResourceNotFoundException("User not found"));

            if (user.getId().equals(essay.getUser().getId())) {
                essay.setBody(essayInsertDTO.getBody());
                essay.setTitle(essayInsertDTO.getTitle());
                essay = essayRepository.save(essay);
            }else {
                throw new ResourceNotFoundException("Only the User I create can update");
            }

            return new EssayDTO(essay);

    }
    /* Retorna uma lista com todos os comentários de uma Essay de ID = id */
    public List<EssayCommentDTO> listComments(Long id){
        Essay essay = essayRepository.getOne(id);
        Set<Comment> comments = essay.getComments();

        List<EssayCommentDTO> queryResult = new ArrayList<EssayCommentDTO>();
        for(Comment comment : comments){
            queryResult.add(new EssayCommentDTO(comment));
        }
        return queryResult;
    }
}
