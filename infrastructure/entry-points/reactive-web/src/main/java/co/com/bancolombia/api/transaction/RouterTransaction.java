package co.com.bancolombia.api.transaction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class RouterTransaction {

  @Value("${path.with-id}")
  private String pathWithId;

  @Bean
  public RouterFunction<ServerResponse> transactionRouter(HandlerV1 handlerV1) {
    return RouterFunctions
        .route()
        .path("/api/v1", builder -> builder
            .GET(pathWithId.concat("/{userId}"), handlerV1::getExpense)
            .POST("/transaction", handlerV1::createTransaction)
            .GET("/transaction/{userId}", handlerV1::getExpenses)
            .PUT(pathWithId, handlerV1::updateExpense)
            .DELETE(pathWithId, handlerV1::deleteExpense)
        ).build();
  }
}
