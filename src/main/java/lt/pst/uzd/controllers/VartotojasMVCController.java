package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.services.VartotojasService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VartotojasMVCController {

    public VartotojasMVCController(VartotojasService vartotojasService) {
        this.vartotojasService = vartotojasService;
    }

    private final VartotojasService vartotojasService;

    @GetMapping("/list-vartotojai")
    public String showAll(ModelMap model) {
        model.put("vartotojai", vartotojasService.findAll());
        return "list-vartotojai";
    }

    @GetMapping("/add-vartotojas")
    public String showAddPage(ModelMap model) {
        model.addAttribute("vartotojas", new Vartotojas());
        return "add-vartotojas";
    }

    @PostMapping("/add-vartotojas")
    public String add(ModelMap model, @ModelAttribute("vartotojas") Vartotojas vartotojas, BindingResult result) {

        if(result.hasErrors()) {
            return "add-vartotojas";
        }
        vartotojasService.save(vartotojas);


        return "redirect:/list-vartotojai";
    }

    @GetMapping("/delete-vartotojas/{id}")
    @Transactional
    public String delete(@PathVariable int id) {
        vartotojasService.deleteById(id);

        return "redirect:/list-vartotojai";
    }

    @GetMapping("/update-vartotojas/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("vartotojas", vartotojasService.findById(id));
        return "add-vartotojas";
    }

    @PostMapping("/update-vartotojas/{id}")
    public String update(ModelMap model, @ModelAttribute("vartotojas") Vartotojas vartotojas, @PathVariable int id,
                         BindingResult result) {
        if(result.hasErrors()) {
            return "add-vartotojas";
        }

        vartotojasService.replaceVartotojas(vartotojas, id);
        return "redirect:/list-vartotojai";
    }
}
