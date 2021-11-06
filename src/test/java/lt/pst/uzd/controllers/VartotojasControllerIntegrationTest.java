package lt.pst.uzd.controllers;

import lt.pst.uzd.UzdApplication;
import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.services.VartotojasService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
public class VartotojasControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private VartotojasService vartotojasService;

    @Test
    void testFindVartotojasById() throws Exception{
        String expected = "{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"37054\"}";
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/Vartotojas/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json(expected);

    }

    @Test
    @Order(1)
    void testAll() {

        String expected = "[{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"37054\"},{\"id\":2,\"vardas\":\"Jonas\",\"telNr\":\"38045\"},{\"id\":3,\"vardas\":\"Petras\",\"telNr\":\"39074\"}]";
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/Vartotojas")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json(expected);
    }

    @Test
    @Order(2)
    void testVartotojasAdd() {
        Vartotojas vartotojas = new Vartotojas(4, "Vytenis", "370111");

        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .post()
                .uri("/Vartotojas")
                .bodyValue(vartotojas)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    @Order(3)
    void testDeleteVartotojasById() {
        Vartotojas vartotojas = new Vartotojas(3, "Petras", "39074");

        WebTestClient.bindToServer()
                .baseUrl("http://localhost:"+port)
                .build().delete()
                .uri("/Vartotojas/"+vartotojas.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(4)
    public void testReplaceVartotojas() throws IOException {
        Vartotojas vartotojas = new Vartotojas(3,"TEST","370001");
        vartotojasService.replaceVartotojas(vartotojas, 3);
        HttpUriRequest request = RequestBuilder.create("PUT")
                .setUri("http://localhost:" + port + "/Vartotojas/3")
                .setEntity(new StringEntity("{\"vardas\":\"TEST\",\"telNr\":\"370001\"}",
                                                        ContentType.APPLICATION_JSON))
                .build();
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.OK.value()));
    }



}
