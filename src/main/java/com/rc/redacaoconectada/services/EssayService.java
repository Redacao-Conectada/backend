package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.Mapper.EssayMapper;
import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.repositories.EssayRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        Essay essay = essayBD.get();

        EssayDTO essayDTO = new EssayDTO();

        EssayMapper.mapsEssaytoEssayDTO(essay, essayDTO);

        return essayDTO;
    }

    @Transactional
    public void deleteEssayById(Long id) {

        Optional<Essay> essayBD = this.essayRepository.findById(id);

        essayRepository.delete(essayBD.get());

    }

    @Transactional
    public EssayDTO insert(EssayDTO essayDTO) {

        Essay essay = new Essay();

        essay.setUpVote(essayDTO.getUpVote());
        essay.setBody(essayDTO.getBody());

        essay = this.essayRepository.save(essay);

        return new EssayDTO(essay);
    }

    @Transactional
    public void upVoteEssay(Long id) {

        Optional<Essay> essayBD = this.essayRepository.findById(id);

        Essay essay = essayBD.get();

        essay.setUpVote(essay.getUpVote() + 1);

    }

}
