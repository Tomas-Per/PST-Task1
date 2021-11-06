package lt.pst.uzd.controllers;

import lt.pst.uzd.UzdApplication;
import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.services.VeiksmaiService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = UzdApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VeiksmaiControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private VeiksmaiService veiksmaiService;

    @Test
    @Order(1)
    void testFindVeiksmaiById() throws Exception{
        String expected = "{\"id\":1,\"veiksmas\":\"insert\",\"vartotojoId\":1,\"data\":\"2021-10-01\"}";
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/Veiksmai/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json(expected);

    }

    @Test
    @Order(2)
    void testAll() {

        String expected = "[{\"id\":1,\"veiksmas\":\"insert\",\"vartotojoId\":1,\"data\":\"2021-10-01\"},{\"id\":2,\"veiksmas\":\"update\",\"vartotojoId\":2,\"data\":\"2021-10-01\"},{\"id\":3,\"veiksmas\":\"delete\",\"vartotojoId\":3,\"data\":\"2021-10-01\"},{\"id\":4,\"veiksmas\":\"insert\",\"vartotojoId\":1,\"data\":\"2021-11-01\"},{\"id\":5,\"veiksmas\":\"delete\",\"vartotojoId\":2,\"data\":\"2021-11-01\"},{\"id\":6,\"veiksmas\":\"update\",\"vartotojoId\":3,\"data\":\"2021-12-01\"}]";
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/Veiksmai")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json(expected);
    }

    @Test
    @Order(3)
    void testVeiksmaiAdd() {
        Veiksmai veiksmas = new Veiksmai(7, "insert", 2, "2021-11-05");

        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .post()
                .uri("/Veiksmai")
                .bodyValue(veiksmas)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    @Order(4)
    void testDeleteVeiksmaiById() {
        Veiksmai veiksmas = new Veiksmai(7, "insert", 2, "2021-11-05");

        WebTestClient.bindToServer()
                .baseUrl("http://localhost:"+port)
                .build().delete()
                .uri("/Veiksmai/"+veiksmas.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(5)
    public void testDeleteAllByVartotojoId() throws IOException {
        Veiksmai veiksmas = new Veiksmai(3, "insert", 3, "2021-11-05");
        veiksmaiService.replaceVeiksmas(veiksmas, 3);
        HttpUriRequest request = RequestBuilder.create("DELETE")
                .setUri("http://localhost:" + port + "/VeiksmaiDel/1")
                .build();
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.OK.value()));
    }
}
