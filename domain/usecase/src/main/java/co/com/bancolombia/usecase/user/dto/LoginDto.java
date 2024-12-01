package co.com.bancolombia.usecase.user.dto;

import co.com.bancolombia.model.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginDto {

  private String token;
  private UserDto userDto;
}
