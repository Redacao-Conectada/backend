package com.rc.redacaoconectada.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_teacher")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User {
  private static final long serialVersionUID = 1L;

  private String schoolRegistration;

  private String schoolNameAsTeacher;

  private String proofImg;

  @OneToMany(mappedBy = "teacher")
  private final List<Correction> corrections = new ArrayList<>();

}
