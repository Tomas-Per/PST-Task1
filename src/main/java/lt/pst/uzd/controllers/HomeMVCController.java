package lt.pst.uzd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeMVCController {

    @GetMapping("/home")
    public String showHome(ModelMap model) {
        return "home";
    }

}
