package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Veiksmai;
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
@WebMvcTest(value = VeiksmaiMVCController.class)
public class VeiksmaiMVCControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VartotojasService vartotojasService;

    @MockBean
    private VeiksmaiService veiksmaiService;

    @InjectMocks
    private VeiksmaiMVCController veiksmaiMVCController;

    @Test
    public void testShowAll() throws Exception {
        List<Veiksmai> veiksmai = new ArrayList<>();
        veiksmai.add(new Veiksmai(1, "insert", 2 ,"2021-11-05"));
        veiksmai.add(new Veiksmai(2, "update", 1, "2021-11-06"));
        when(veiksmaiService.findAll()).thenReturn(veiksmai);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-veiksmai")
                .accept(MediaType.TEXT_HTML);

        MvcResult res = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-veiksmai"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-veiksmai.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("veiksmai"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-veiksmas");

        MvcResult rs = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-veiksmas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-veiksmas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("naujasVeiksmas"))
                .andExpect(MockMvcResultMatchers.model().attribute("naujasVeiksmas",
                        hasProperty("id", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("naujasVeiksmas",
                        hasProperty("veiksmas", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("naujasVeiksmas",
                        hasProperty("vartotojoId", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("naujasVeiksmas",
                        hasProperty("data", emptyOrNullString())))
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception{
        when(veiksmaiService.save(Mockito.any(Veiksmai.class)))
                .thenReturn(new Veiksmai(1, "delete", 2, "2021-11-07"));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-veiksmas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("veiksmas","delete")
                .param("vartotojoId", String.valueOf(2))
                .param("data","2021-11-07")
                .flashAttr("veiksmas", new Veiksmai());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-veiksmai"));

        verify(veiksmaiService).save(Mockito.any(Veiksmai.class));
    }

    @Test
    public void testShowUpdatePage() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders.get("/update-veiksmas/{id}", 1);

        MvcResult rs = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-veiksmas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-veiksmas.jsp"))
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        when(veiksmaiService.replaceVeiksmas(Mockito.any(Veiksmai.class), Mockito.anyInt()))
                .thenReturn(new Veiksmai(1, "delete", 2, "2021-11-07"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-veiksmas/" + 1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("veiksmas","delete")
                .param("vartotojoId", String.valueOf(2))
                .param("data","2021-11-07")
                .flashAttr("veiksmas", new Veiksmai());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-veiksmai"));

        verify(veiksmaiService).replaceVeiksmas(Mockito.any(Veiksmai.class), Mockito.anyInt());
    }

    @Test
    public void testDelete() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/delete-veiksmas/{id}", Mockito.anyInt())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", String.valueOf(1));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-veiksmai"));

    }
}
