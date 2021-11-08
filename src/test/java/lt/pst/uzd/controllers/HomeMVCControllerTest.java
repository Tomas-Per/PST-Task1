package lt.pst.uzd.controllers;

import lt.pst.uzd.services.VartotojasService;
import lt.pst.uzd.services.VeiksmaiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = HomeMVCController.class)
public class HomeMVCControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VartotojasService vartotojasService;

    @MockBean
    private VeiksmaiService veiksmaiService;

    @InjectMocks
    HomeMVCController homeMVCController;

    @Test
    void testShowHome() throws Exception {

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/home")
                .accept(MediaType.TEXT_HTML);

        MvcResult res = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/home.jsp"))
                .andReturn();
    }
}
