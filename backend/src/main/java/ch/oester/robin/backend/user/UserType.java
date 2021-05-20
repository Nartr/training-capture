package ch.oester.robin.backend.user;

import org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor;

public enum UserType {
  VISITOR,
  ATHLETE(),
  TRAINER("ACCEPT_ATHLETE"),
  ADMIN;

  private String[] privileges;

  UserType(String... privileges) {
    this.privileges = privileges;
  }

  public String[] getPrivileges() {
    return privileges;
  }
}
