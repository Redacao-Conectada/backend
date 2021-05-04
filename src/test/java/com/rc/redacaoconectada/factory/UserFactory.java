package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class UserFactory {

  public static User createUser() {
    return new User(3L, "123.456.789.11", "Bob", "bob@gmail.com", "12345678", Instant.now(),
            "Superior Completo", "UFCG", "PB", "Patos", "");
  }
}
