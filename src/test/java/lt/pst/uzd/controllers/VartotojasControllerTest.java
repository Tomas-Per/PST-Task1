package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.services.VartotojasService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = VartotojasController.class)
public class VartotojasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VartotojasService vartotojasService;

    @AfterEach
    void tearDown() {
        reset(vartotojasService);
    }

    @Test
    void testAll() throws Exception {
        List<Vartotojas> vartotojai = new ArrayList<>();
        vartotojai.add(new Vartotojas(1, "Tomas", "+370111"));
        vartotojai.add(new Vartotojas(2, "Jonas" , "+370222"));
        when(vartotojasService.findAll()).thenReturn(vartotojai);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Vartotojas")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        String expected = "[{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"+370111\"},{\"id\":2,\"vardas\":\"Jonas\",\"telNr\":\"+370222\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testVartotojasById() throws Exception {
        Vartotojas vartotojas = new Vartotojas(1, "Tomas", "+370111");
        when(vartotojasService.findById(Mockito.anyInt())).thenReturn(vartotojas);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Vartotojas/{id}", vartotojas.getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"+370111\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testAdd() throws Exception {
        Vartotojas vartotojas = new Vartotojas(1, "Tomas", "+370111");
        when(vartotojasService.save(Mockito.any(Vartotojas.class))).thenReturn(vartotojas);

        String json = "{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"+370111\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/Vartotojas")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testDeleteById() throws Exception
    {
        Vartotojas vartotojas = new Vartotojas(1, "Tomas", "+370111");
        when(vartotojasService.save(Mockito.any(Vartotojas.class))).thenReturn(vartotojas);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Vartotojas/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Vartotojas vartotojas = new Vartotojas(1, "Tomas", "+370111");
        when(vartotojasService.save(Mockito.any(Vartotojas.class))).thenReturn(vartotojas);

        String json = "{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"+370111\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(
                        "/Vartotojas")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

//    @Test
//    void testReplaceVartotojas() throws Exception {
//        Vartotojas vartotojas = new Vartotojas(1, "Tomas", "+370111");
//        when(vartotojasService.replaceVartotojas(Mockito.any(Vartotojas.class), Mockito.anyInt()))
//                .thenReturn(vartotojas);
//
//        String json = "{\"id\":1,\"vardas\":\"Tomas\",\"telNr\":\"+370111\"}";
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .put(
//                        "/Vartotojas" + 1)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//    }

}
