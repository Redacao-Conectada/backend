package com.rc.redacaoconectada.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_student")
@NoArgsConstructor
public class Student extends User {
  private static final long serialVersionUID = 1L;
}
