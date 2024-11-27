package co.com.bancolombia.api.jwt;

import co.com.bancolombia.api.config.JwtProvider;
import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.usecase.user.JwtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtGatewayImpl implements JwtGateway {

  private final JwtProvider jwtProvider;

  @Override
  public String generateToken(UserDto userDto) {
    return this.jwtProvider.generateToken(userDto);
  }
}
