package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.services.VartotojasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeMVCController {

    public HomeMVCController(VartotojasService vartotojasService) {
        this.vartotojasService = vartotojasService;
    }

    private final VartotojasService vartotojasService;

    @GetMapping("/home")
    public String showHome(ModelMap model) {
        model.addAttribute("vartotojas", new Vartotojas());
        return "home";

    }

    @PostMapping("/home")
    public String login(ModelMap model, @ModelAttribute("vartotojas") Vartotojas vartotojas, BindingResult result,
                        HttpSession session) {
        if(result.hasErrors()) {
            return "home";
        }
        Vartotojas loggedIn = vartotojasService.findByVardas(vartotojas.getVardas());
        session.setAttribute("vartotojas", loggedIn);


        return "home";
    }
}
