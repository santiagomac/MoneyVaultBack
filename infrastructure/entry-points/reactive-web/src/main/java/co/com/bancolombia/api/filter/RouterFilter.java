package co.com.bancolombia.api.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFilter {
    @Bean
    public RouterFunction<ServerResponse> filterRouter(HandlerFilter handlerFilter) {
        return route(
                GET("/api/v1/transactions"), handlerFilter::filterPastWeek);
    }
}
