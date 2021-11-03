package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.services.VeiksmaiService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class VeiksmaiController {

    public VeiksmaiController(VeiksmaiService veiksmaiService) {
        this.veiksmaiService = veiksmaiService;
    }

    private final VeiksmaiService veiksmaiService;

    @GetMapping("/Veiksmai/{id}")
    @ResponseBody
    public Veiksmai getVeiksmas(@PathVariable("id") int id) {
        return veiksmaiService.findById(id);
    }

    @GetMapping("Veiksmai")
    public Iterable<Veiksmai> all() {
        return veiksmaiService.findAll();
    }

    @PostMapping("/Veiksmai")
    public Veiksmai add(Veiksmai veiksmas) {
        return veiksmaiService.save(veiksmas);
    }

    @DeleteMapping("/Veiksmai/{id}")
    public void deleteById(@PathVariable("id") int id) {
        try {
            veiksmaiService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/VeiksmaiDel/{id}")
    @Transactional
    public void deleteAllByVartotojoId(@PathVariable("id") int id) {
        veiksmaiService.deleteAllByVartotojoId(id);
    }

}
