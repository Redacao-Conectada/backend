package com.rc.redacaoconectada.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_admin")
@NoArgsConstructor
public class Admin extends User {
  private static final long serialVersionUID = 1L;
}
