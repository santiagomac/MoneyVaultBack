package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.user.UserDto;

public interface JwtGateway {
  String generateToken(UserDto userDto);
}
