package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.dtos.EssayInsertDTO;
import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.CommentRepository;
import com.rc.redacaoconectada.repositories.EssayRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.DatabaseException;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EssayService {

    @Autowired
    private EssayRepository essayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

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

    public void deleteEssayById(Long id) {
        try {
            essayRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("ID not Found " + id);
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
        essay.setUpVote(essayInsertDTO.getUpVote());
        essay.setBody(essayInsertDTO.getBody());

        return essay;
    }

    @Transactional
    public void upVoteEssay(Long id) {

        Optional<Essay> essayBD = this.essayRepository.findById(id);

        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        essay.setUpVote(essay.getUpVote() + 1);

    }

    @Transactional
    public EssayDTO update(Long id, EssayInsertDTO essayInsertDTO) {
        //Falta a verificacao de so ser atualizada caso nao tenha solicitacao de correcao
        try {
            Essay essay = essayRepository.getOne(id);
            essay = mapsEssayInsertDTOtoEssay(essayInsertDTO);
            essay = essayRepository.save(essay);
            return new EssayDTO(essay);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("ID not Found " + id);
        }
    }

}
