package ch.oester.robin.backend.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence encodedPassword, String realEncodedPassword) {
    return realEncodedPassword.equals(encodedPassword.toString());
  }
}
