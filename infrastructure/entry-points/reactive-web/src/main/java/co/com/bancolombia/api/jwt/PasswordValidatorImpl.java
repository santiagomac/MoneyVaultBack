package co.com.bancolombia.api.jwt;

import co.com.bancolombia.usecase.user.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordValidatorImpl implements PasswordValidator {

  private final PasswordEncoder passwordEncoder;

  @Override
  public boolean validatePassword(String password, String encodedPassword) {
    return passwordEncoder.matches(password, encodedPassword);
  }
}
