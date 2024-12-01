package co.com.bancolombia.usecase.exceptions;

public class BadCredentialsLoginException extends RuntimeException{

  public BadCredentialsLoginException() {
    super("Invalid credentials");
  }

  public BadCredentialsLoginException(String message) {
    super(message);
  }

  // Constructor que incluye mensaje y causa
  public BadCredentialsLoginException(String message, Throwable cause) {
    super(message, cause);
  }
}
