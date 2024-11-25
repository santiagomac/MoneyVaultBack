package co.com.bancolombia.api;

import co.com.bancolombia.api.transaction.HandlerV1;
import co.com.bancolombia.api.transaction.RouterRest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@ContextConfiguration(classes = {RouterRest.class, HandlerV1.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testListenGETUseCaseV1() {
        webTestClient.get()
                .uri("/api/v1/usecase/path")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEmpty();
                        }
                );
    }


    @Test
    void testListenGETOtherUseCaseV1() {
        webTestClient.get()
                .uri("/api/v1/otherusercase/path")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEmpty();
                        }
                );
    }

    @Test
    void testListenPOSTUseCaseV1() {
        webTestClient.post()
                .uri("/api/v1/usecase/otherpath")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue("")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEmpty();
                        }
                );
    }
}
