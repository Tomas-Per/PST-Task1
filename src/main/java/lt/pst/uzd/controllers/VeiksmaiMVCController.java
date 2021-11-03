package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.services.VeiksmaiService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class VeiksmaiMVCController {

    public VeiksmaiMVCController(VeiksmaiService veiksmaiService) {
        this.veiksmaiService = veiksmaiService;
    }

    private final VeiksmaiService veiksmaiService;

    @GetMapping("/list-veiksmai")
    public String showAll(ModelMap model) {
        model.put("veiksmai", veiksmaiService.findAll());
        model.put("names", veiksmaiService.getNames());

        return "list-veiksmai";
    }

    @GetMapping("/add-veiksmas")
    public String showAddPage(ModelMap model) {
        model.addAttribute("naujasVeiksmas", new Veiksmai());
        return "add-veiksmas";
    }

    @PostMapping("/add-veiksmas")
    public String add(ModelMap model, @ModelAttribute("naujasVeiksmas") Veiksmai naujasVeiksmas, BindingResult result,
                      HttpSession session) {

        if(result.hasErrors()) {
            return "add-veiksmas";
        }

        Vartotojas var = (Vartotojas) session.getAttribute("vartotojas");

        naujasVeiksmas.setId(0);
        naujasVeiksmas.setVartotojoId(var.getId());
        veiksmaiService.save(naujasVeiksmas);

        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setId(0);
        veiksmas.setVeiksmas("insert");
        veiksmas.setVartotojoId(var.getId());
        veiksmas.setData(LocalDate.now().toString());

        veiksmaiService.save(veiksmas);

        return "redirect:/list-veiksmai";
    }

    @GetMapping("/delete-veiksmas/{id}")
    @Transactional
    public String delete(@PathVariable int id, HttpSession session) {
        veiksmaiService.deleteById(id);

        Vartotojas var = (Vartotojas) session.getAttribute("vartotojas");

        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setId(0);
        veiksmas.setVeiksmas("delete");
        veiksmas.setVartotojoId(var.getId());
        veiksmas.setData(LocalDate.now().toString());

        veiksmaiService.save(veiksmas);
        return "redirect:/list-veiksmai";
    }

    @GetMapping("/update-veiksmas/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("naujasVeiksmas", veiksmaiService.findById(id));
        return "add-veiksmas";
    }

    @PostMapping("/update-veiksmas/{id}")
    public String update(ModelMap model, @ModelAttribute("naujasVeiksmas") Veiksmai naujasVeiksmas, @PathVariable int id,
                         BindingResult result , HttpSession session) {
        if(result.hasErrors()) {
            return "add-veiksmas";
        }

        Vartotojas var = (Vartotojas) session.getAttribute("vartotojas");

        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setId(0);
        veiksmas.setVeiksmas("update");
        veiksmas.setVartotojoId(var.getId());
        veiksmas.setData(LocalDate.now().toString());

        veiksmaiService.save(veiksmas);

        naujasVeiksmas.setVartotojoId(var.getId());
        veiksmaiService.replaceVeiksmas(naujasVeiksmas, id);
        return "redirect:/list-veiksmai";
    }
}
