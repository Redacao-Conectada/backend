package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.services.validation.UserInsertValid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@UserInsertValid
@Data
@NoArgsConstructor
public class UserInsertDTO extends UserDTO {
  private static final long serialVersionUID = 1L;

  @NotBlank(message = "Senha requerida")
  private String password;

}
