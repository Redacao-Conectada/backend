package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.CorrectionDTO;
import com.rc.redacaoconectada.dtos.CorrectionInsertDTO;
import com.rc.redacaoconectada.entities.Correction;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.Role;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.CorrectionRepository;
import com.rc.redacaoconectada.repositories.EssayRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CorrectionService {
    @Autowired
    private CorrectionRepository correctionRepository;

    @Autowired
    private EssayRepository essayRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<CorrectionDTO> findAll(PageRequest pageRequest) {

        Page<Correction> correctionsBD = this.correctionRepository.findAll(pageRequest);

        return correctionsBD.map(CorrectionDTO::new);
    }

    @Transactional(readOnly = true)
    public CorrectionDTO findCorrectionById(Long id) {

        Optional<Correction> correctionBD = this.correctionRepository.findById(id);


        Correction correction = correctionBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));
        return new CorrectionDTO(correction);
    }

    @Transactional
    public CorrectionDTO insert(CorrectionInsertDTO correctionInsertDTO) {

        Correction correction = mapsCorrectionInsertDTOtoCorrection(correctionInsertDTO);

        correction = this.correctionRepository.save(correction);

        return new CorrectionDTO(correction);
    }

    private Correction mapsCorrectionInsertDTOtoCorrection(CorrectionInsertDTO correctionInsertDTO){

        Correction correction = new Correction();

        Optional<User> userBD = userRepository.findById(correctionInsertDTO.getIdTeacherUser());

        User user = userBD.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        isTeacher(user);

        Optional<Essay> essayBD = essayRepository.findById(correctionInsertDTO.getIdEssay());

        Essay essay = essayBD.orElseThrow(() -> new ResourceNotFoundException("Essay not found"));

        correction.setTeacher(user);
        correction.setEssay(essay);
        correction.setCreated_date(correctionInsertDTO.getCreatedDate());
        correction.setCompetences(correctionInsertDTO.getCompetences());
        correctionRepository.save(correction);

        essay.setCorrection(correction);
        essayRepository.save(essay);

        return correction;
    }

    public void isTeacher(User user){
        if (user == null){
            throw new ResourceNotFoundException("User not found");
        }
        boolean checkRoleTeacher = false;
        try {
            for (Role r: user.getRoles()){
                if (r.getAuthority().equals("ROLE_TEACHER")){
                    checkRoleTeacher = true;
                }
            }
        }catch(NullPointerException npe){
            throw new ResourceNotFoundException("User doesn't have role");
        }
        if (!checkRoleTeacher){
            throw new ResourceNotFoundException("User teacher role is not found");
        }
    }
}
