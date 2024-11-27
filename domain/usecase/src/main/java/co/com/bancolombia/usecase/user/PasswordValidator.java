package co.com.bancolombia.usecase.user;

public interface PasswordValidator {

  boolean validatePassword(String password, String encodedPassword);
}
