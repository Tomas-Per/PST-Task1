package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.services.VartotojasService;
import lt.pst.uzd.services.VeiksmaiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VartotojasMVCController.class)
public class VartotojasMVCControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VartotojasService vartotojasService;

    @MockBean
    private VeiksmaiService veiksmaiService;

    @InjectMocks
    VartotojasMVCController vartotojasMVCController;

    @Test
    void testShowAll() throws Exception {
        List<Vartotojas> vartotojai = new ArrayList<>();
        vartotojai.add(new Vartotojas(1, "Tomas", "+371111"));
        vartotojai.add(new Vartotojas(2, "Jonas", "+372222"));
        when(vartotojasService.findAll()).thenReturn(vartotojai);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-vartotojai")
                .accept(MediaType.TEXT_HTML);

        MvcResult res = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-vartotojai"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-vartotojai.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojai"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-vartotojas");

        MvcResult rs = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-vartotojas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-vartotojas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojas"))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas",
                        hasProperty("id", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas",
                        hasProperty("vardas", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas",
                        hasProperty("telNr", emptyOrNullString())))
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception{
        when(vartotojasService.save(Mockito.any(Vartotojas.class)))
                .thenReturn(new Vartotojas("Tomas","+37011"));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-vartotojas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("vardas","Tomas")
                .param("telNr","+37011")
                .flashAttr("vartotojas",new Vartotojas());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-vartotojai"));

        verify(vartotojasService).save(Mockito.any(Vartotojas.class));
    }

    @Test
    public void testShowUpdatePage() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders.get("/update-vartotojas/{id}", 1);

        MvcResult rs = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-vartotojas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-vartotojas.jsp"))
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        when(vartotojasService.replaceVartotojas(Mockito.any(Vartotojas.class), Mockito.anyInt()))
                .thenReturn(new Vartotojas("Tomas","+37011"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-vartotojas/" + 1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id",String.valueOf(1))
                .param("vardas","Tomas")
                .param("telNr","+37011");

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-vartotojai"));

        verify(vartotojasService).replaceVartotojas(Mockito.any(Vartotojas.class), Mockito.anyInt());
    }

    @Test
    public void testDelete() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/delete-vartotojas/{id}", Mockito.anyInt())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", String.valueOf(1));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-vartotojai"));

    }
}
