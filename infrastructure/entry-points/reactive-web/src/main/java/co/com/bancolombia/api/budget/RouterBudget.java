package co.com.bancolombia.api.budget;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterBudget {

    @Bean
    public RouterFunction<ServerResponse> budgetRoute(HandlerBudget handlerBudget) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                        .POST("/budget", handlerBudget::createBudget)
                        .GET("/budget/{userId}", handlerBudget::getAllBudgets)
                        .GET("/budget/{id}", handlerBudget::getBudgetById)
                        .PUT("/budget/{id}", handlerBudget::updateBudget)
                        .DELETE("/budget/{id}", handlerBudget::deleteBudget))
                .build();
    }
}
