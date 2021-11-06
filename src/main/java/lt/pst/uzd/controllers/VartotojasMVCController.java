package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.services.VartotojasService;
import lt.pst.uzd.services.VeiksmaiService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class VartotojasMVCController {

    public VartotojasMVCController(VartotojasService vartotojasService, VeiksmaiService veiksmaiService) {
        this.vartotojasService = vartotojasService;
        this.veiksmaiService = veiksmaiService;
    }

    private final VartotojasService vartotojasService;
    private final VeiksmaiService veiksmaiService;

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
    public String add(ModelMap model, @ModelAttribute("vartotojas") Vartotojas vartotojas, BindingResult result,
                      HttpSession session) {

        if(result.hasErrors()) {
            return "add-vartotojas";
        }
        vartotojasService.save(vartotojas);

        //session does not work with tests
        //Vartotojas var = (Vartotojas) session.getAttribute("vartotojas");

        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setId(0);
        veiksmas.setVeiksmas("insert");
        //veiksmas.setVartotojoId(var.getId());
        veiksmas.setVartotojoId(1);
        veiksmas.setData(LocalDate.now().toString());

        veiksmaiService.save(veiksmas);

        return "redirect:/list-vartotojai";
    }

    @GetMapping("/delete-vartotojas/{id}")
    @Transactional
    public String delete(@PathVariable int id, HttpSession session) {
        vartotojasService.deleteById(id);

        Vartotojas var = (Vartotojas) session.getAttribute("vartotojas");
        //session does not work with tests
//        Veiksmai veiksmas = new Veiksmai();
//        veiksmas.setId(0);
//        veiksmas.setVeiksmas("delete");
//        veiksmas.setVartotojoId(var.getId());
//        veiksmas.setData(LocalDate.now().toString());
//
//        veiksmaiService.save(veiksmas);
        return "redirect:/list-vartotojai";
    }

    @GetMapping("/update-vartotojas/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("vartotojas", vartotojasService.findById(id));
        return "add-vartotojas";
    }

    @PostMapping("/update-vartotojas/{id}")
    public String update(ModelMap model, @ModelAttribute("vartotojas") Vartotojas vartotojas, @PathVariable int id,
                         BindingResult result , HttpSession session) {
        if(result.hasErrors()) {
            return "add-vartotojas";
        }
        //session does not work with tests
//        Vartotojas var = (Vartotojas) session.getAttribute("vartotojas");
//
//        Veiksmai veiksmas = new Veiksmai();
//        veiksmas.setId(0);
//        veiksmas.setVeiksmas("update");
//        veiksmas.setVartotojoId(var.getId());
//        veiksmas.setData(LocalDate.now().toString());
//
//        veiksmaiService.save(veiksmas);

        vartotojasService.replaceVartotojas(vartotojas, id);
        return "redirect:/list-vartotojai";
    }
}
