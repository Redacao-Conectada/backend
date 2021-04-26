package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  @NotBlank(message = "CPF requerido")
  private String cpf;

  @NotBlank(message = "Nome requerido")
  private String name;

  @NotBlank(message = "Email obrigatório")
  @Email(message = "E-mail inválido")
  private String email;

  @Past(message = "A data de nascimento não pode ser futura")
  private Instant birthdate;

  @NotBlank(message = "Grau escolar requerido")
  private String graduation;

  @NotBlank(message = "Nome da escola requerido")
  private String schoolName;

  @NotBlank(message = "Nome do estado requerido")
  private String state;

  @NotBlank(message = "Nome da cidade requerido")
  private String city;

  private String image;

  private final List<RoleDTO> roles = new ArrayList<>();

  public UserDTO(User user) {
    this.id = user.getId();
    this.cpf = user.getCpf();
    this.name = user.getName();
    this.email = user.getEmail();
    this.birthdate = user.getBirthdate();
    this.graduation = user.getGraduation();
    this.schoolName = user.getSchoolName();
    this.state = user.getState();
    this.city = user.getCity();

    user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
  }

}
