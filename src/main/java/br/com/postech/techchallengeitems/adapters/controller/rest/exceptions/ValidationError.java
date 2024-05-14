package br.com.postech.techchallengeitems.adapters.controller.rest.exceptions;

import lombok.Data;

@Data
public class ValidationError {
  private String message;
  private String field;
  private String rejectedValue;



}
