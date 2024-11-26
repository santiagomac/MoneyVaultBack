package co.com.bancolombia.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterUser {
    @Bean
    public RouterFunction<ServerResponse> userRouter(Handler handler) {
        return route(
                POST("/api/v1/auth/signup"), handler::registerUser)
                .andRoute(POST("/api/v1/auth/signin"), handler::login)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));
    }
}
