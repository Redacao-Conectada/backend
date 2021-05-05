package com.rc.redacaoconectada.controllers.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {
  private static final long serialVersionUID = 1L;

  private final List<FieldMessage> erros = new ArrayList<>();

  public void addError(String fieldName, String message) {
    this.erros.add(new FieldMessage(fieldName, message));
  }
}
