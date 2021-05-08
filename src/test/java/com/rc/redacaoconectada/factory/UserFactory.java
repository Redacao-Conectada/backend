package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Role;
import com.rc.redacaoconectada.entities.Student;
import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class UserFactory {

  public static User createUser() {
    return new User(3L, "123.456.789.11", "Bob", "bob@gmail.com", "12345678", Instant.now(),
            "Superior Completo", "UFCG", "PB", "Patos", "");
  }

  public static User createUserStudent() {
    Role role = RoleFactory.createRoleStudent();
    User user = new User(3L, "123.456.789.11", "Bob", "bob@gmail.com", "12345678", Instant.now(),
            "Superior Completo", "UFCG", "PB", "Patos", "");
    user.getRoles().add(role);

    return user;
  }

  public static User createUserTeacher() {
    Role role = RoleFactory.createRoleTeacher();
    User user = new User(3L, "123.456.789.11", "Bob", "bob@gmail.com", "12345678", Instant.now(),
            "Superior Completo", "UFCG", "PB", "Patos", "");
    user.getRoles().add(role);

    return user;
  }

  public static User createUserAdmin() {
    Role role = RoleFactory.createRoleAdmin();
    User user = new User(3L, "123.456.789.11", "Bob", "bob@gmail.com", "12345678", Instant.now(),
            "Superior Completo", "UFCG", "PB", "Patos", "");
    user.getRoles().add(role);

    return user;
  }
}
