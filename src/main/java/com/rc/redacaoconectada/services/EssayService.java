package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.Mapper.EssayMapper;
import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.repositories.EssayRepository;
import com.rc.redacaoconectada.services.exceptions.DatabaseException;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EssayService {

    @Autowired
    private EssayRepository essayRepository;

    @Transactional
    public List<EssayDTO> findAll() {

        List<Essay> essaysBD = this.essayRepository.findAll();

        List<EssayDTO> essaysDTO = new ArrayList<>();

        EssayMapper.mapsEssaystoEssaysDTO(essaysBD, essaysDTO);

        return essaysDTO;
    }

    @Transactional
    public EssayDTO findEssayById(Long id) {

        Optional<Essay> essayBD = this.essayRepository.findById(id);

        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        EssayDTO essayDTO = new EssayDTO();

        EssayMapper.mapsEssaytoEssayDTO(essay, essayDTO);

        return essayDTO;
    }

    @Transactional
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
    public EssayDTO insert(EssayDTO essayDTO) {

        Essay essay = new Essay();

        EssayMapper.mapsEssayDTOtoEssay(essayDTO, essay);

        essay = this.essayRepository.save(essay);

        return new EssayDTO(essay);
    }

    @Transactional
    public void upVoteEssay(Long id) {

        Optional<Essay> essayBD = this.essayRepository.findById(id);

        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        essay.setUpVote(essay.getUpVote() + 1);

    }

    @Transactional
    public EssayDTO update(Long id, EssayDTO essayDTO) {
        //Falta a verificacao de so ser atualizada caso nao tenha solicitacao de correcao
        try {
            Essay essay = essayRepository.getOne(id);
            EssayMapper.mapsEssayDTOtoEssay(essayDTO, essay);
            essay = essayRepository.save(essay);
            return new EssayDTO(essay);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("ID not Found " + id);
        }
    }

}
