package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.services.VeiksmaiService;
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

@WebMvcTest(value = VeiksmaiController.class)
public class VeiksmaiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiksmaiService veiksmaiService;

    @AfterEach
    void tearDown() {
        reset(veiksmaiService);
    }

    @Test
    void testAll() throws Exception {
        List<Veiksmai> veiksmai = new ArrayList<>();
        veiksmai.add(new Veiksmai(1, "insert", 1, "2021-11-05"));
        veiksmai.add(new Veiksmai(2, "update", 2, "2021-11-06"));
        when(veiksmaiService.findAll()).thenReturn(veiksmai);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Veiksmai")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        String expected = "[{\"id\":1,\"veiksmas\":\"insert\",\"vartotojoId\":1,\"data\":\"2021-11-05\"},{\"id\":2,\"veiksmas\":\"update\",\"vartotojoId\":2,\"data\":\"2021-11-06\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testVeiksmasById() throws Exception {
        Veiksmai veiksmas = new Veiksmai(1, "insert", 1, "2021-11-05");
        when(veiksmaiService.findById(Mockito.anyInt())).thenReturn(veiksmas);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Veiksmai/{id}", veiksmas.getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"veiksmas\":\"insert\",\"vartotojoId\":1,\"data\":\"2021-11-05\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testAdd() throws Exception {
        Veiksmai veiksmas = new Veiksmai(1, "insert", 1, "2021-11-05");
        when(veiksmaiService.save(Mockito.any(Veiksmai.class))).thenReturn(veiksmas);

        String json = "{\"id\":1,\"veiksmas\":\"insert\",\"vartotojoId\":1,\"data\":\"2021-11-05\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/Veiksmai")
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
        Veiksmai veiksmas = new Veiksmai(1, "insert", 1, "2021-11-05");
        when(veiksmaiService.save(Mockito.any(Veiksmai.class))).thenReturn(veiksmas);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Veiksmai/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByVartotojoId() throws Exception {
        Veiksmai veiksmas = new Veiksmai(1, "insert", 1, "2021-11-05");
        when(veiksmaiService.save(Mockito.any(Veiksmai.class))).thenReturn(veiksmas);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(
                        "/VeiksmaiDel/" + 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
