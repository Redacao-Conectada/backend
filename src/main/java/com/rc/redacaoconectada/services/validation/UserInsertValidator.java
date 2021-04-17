package com.rc.redacaoconectada.services.validation;

import com.rc.redacaoconectada.dtos.UserInsertDTO;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.controllers.exceptions.FieldMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void initialize(UserInsertValid ann) {
  }

  @Override
  public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
    log.info("method=isValid, msg=valid email {}", dto.getEmail());
    List<FieldMessage> list = new ArrayList<>();

    User user = userRepository.findByEmail(dto.getEmail());
    if (user != null) {
      log.error("method=isValid, msg=email {} already exists", dto.getEmail());
      list.add(new FieldMessage("email", "E-mail já existe"));
    }

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
              .addConstraintViolation();
    }
    return list.isEmpty();
  }
}