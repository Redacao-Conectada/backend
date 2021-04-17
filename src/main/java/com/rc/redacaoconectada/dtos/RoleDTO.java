package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String authority;

  public RoleDTO(Role entity) {
    id = entity.getId();
    authority = entity.getAuthority();
  }

}

