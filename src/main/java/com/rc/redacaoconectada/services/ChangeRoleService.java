package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.UserChangeRoleDto;
import com.rc.redacaoconectada.dtos.UserChangeRoleInsertDTO;
import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.ChangeRoleRequestRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChangeRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChangeRoleRequestRepository changeRoleRequestRepository;

    @Transactional
    public UserChangeRoleDto requestChangeRole(UserChangeRoleInsertDTO requestChangeRole) {
        ChangeRoleRequest changeRoleRequest = new ChangeRoleRequest();
        dtoChangeRoleToEntityConverter(requestChangeRole, changeRoleRequest);
        changeRoleRequest = changeRoleRequestRepository.save(changeRoleRequest);
        return new UserChangeRoleDto(changeRoleRequest);
    }

    private void dtoChangeRoleToEntityConverter(UserChangeRoleInsertDTO requestChangeRoleDTO, ChangeRoleRequest changeRoleRequest) {

        Optional<User> userBD = userRepository.findById(requestChangeRoleDTO.getIdUser());
        User user = userBD.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        changeRoleRequest.setProof_img(requestChangeRoleDTO.getProof_img());
        changeRoleRequest.setSchool_registration(requestChangeRoleDTO.getSchool_registration());
        changeRoleRequest.setSchool_name_as_teacher(requestChangeRoleDTO.getSchool_name_as_teacher());
        changeRoleRequest.setUser(user);

    }
}
