package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.ChangeRoleDTO;
import com.rc.redacaoconectada.dtos.UserChangeRoleDTO;
import com.rc.redacaoconectada.dtos.UserChangeRoleInsertDTO;
import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import com.rc.redacaoconectada.entities.Role;
import com.rc.redacaoconectada.entities.Teacher;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.ChangeRoleRequestRepository;
import com.rc.redacaoconectada.repositories.RoleRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChangeRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChangeRoleRequestRepository changeRoleRequestRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserChangeRoleDTO requestChangeRole(UserChangeRoleInsertDTO requestChangeRole) {
        ChangeRoleRequest changeRoleRequest = new ChangeRoleRequest();
        dtoChangeRoleToEntityConverter(requestChangeRole, changeRoleRequest);
        changeRoleRequestRepository.save(changeRoleRequest);
        return new UserChangeRoleDTO(changeRoleRequest);
    }

    private void dtoChangeRoleToEntityConverter(UserChangeRoleInsertDTO requestChangeRoleDTO, ChangeRoleRequest changeRoleRequest) {

        Optional<User> userBD = userRepository.findById(requestChangeRoleDTO.getIdUser());
        User user = userBD.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        changeRoleRequest.setProof_img(requestChangeRoleDTO.getProof_img());
        changeRoleRequest.setSchool_registration(requestChangeRoleDTO.getSchool_registration());
        changeRoleRequest.setSchool_name_as_teacher(requestChangeRoleDTO.getSchool_name_as_teacher());
        changeRoleRequest.setUser(user);

    }

    @Transactional
    public UserDTO approveChangeRole(Long id){
        Optional<User> obj = userRepository.findById(id);
        User user = obj.orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));
        for(ChangeRoleRequest c : changeRoleRequestRepository.findAll()) {
            if (c.getUser().equals(user)) {
                Teacher teacher = new Teacher();
                userToTeacherConverter(user, teacher, c);
                changeRoleRequestRepository.delete(c);
                userRepository.save(teacher);
                //userRepository.delete(user);
                return new UserDTO(teacher);

            }
        }
        throw new ResourceNotFoundException("Not Change Role Requests!");
    }

    @Transactional(readOnly = true)
    public Page<ChangeRoleDTO> findAllPagedChangeRoleRequest(PageRequest pagerequest){
        Page<ChangeRoleRequest> changeRoleRequest = changeRoleRequestRepository.findAll(pagerequest);
        return changeRoleRequest.map(ChangeRoleDTO::new);
    }


    @Transactional
    public UserDTO denniedChangeRole(Long id){
        Optional<User> obj = userRepository.findById(id);
        User user = obj.orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));
        for(ChangeRoleRequest c : changeRoleRequestRepository.findAll()) {
            if (c.getUser().equals(user)) {
                changeRoleRequestRepository.delete(c);
                return new UserDTO(user);
            }
        }
        throw new ResourceNotFoundException("Not Change Role Requests!");

    }


    private void userToTeacherConverter(User user, Teacher teacher, ChangeRoleRequest changeRoleRequest){
        var schoolNameAsTeacher = changeRoleRequest.getSchool_name_as_teacher();
        var schoolRegistration = changeRoleRequest.getSchool_registration();
        var proofImg = changeRoleRequest.getProof_img();
        teacher.setName(user.getName());
        teacher.setSchoolName(user.getSchoolName());
        teacher.setGraduation(user.getGraduation());
        teacher.setBirthdate(user.getBirthdate());
        teacher.setCity(user.getCity());
        teacher.setCpf(user.getCpf());
        teacher.setEmail(user.getEmail());
        teacher.setPassword(user.getPassword());
        teacher.setState(user.getState());
        teacher.setProofImg(proofImg);
        teacher.setSchoolRegistration(schoolRegistration);
        teacher.setSchoolNameAsTeacher(schoolNameAsTeacher);
        Role role = roleRepository.getOne(1L);
        teacher.getRoles().add(role);
        Role role2 = roleRepository.getOne(2L);
        teacher.getRoles().add(role2);
    }
}
