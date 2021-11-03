package lt.pst.uzd.controllers;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.services.VartotojasService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class VartotojasController {

    public VartotojasController(VartotojasService vartotojasService) {
        this.vartotojasService = vartotojasService;
    }

    private final VartotojasService vartotojasService;

    @GetMapping("/Vartotojas/{id}")
    @ResponseBody
    public Vartotojas getVartotojas(@PathVariable("id") int id) {
        return vartotojasService.findById(id);
    }

    @GetMapping("/Vartotojas")
    public Iterable<Vartotojas> all() {
        return vartotojasService.findAll();
    }

    @PostMapping("/Vartotojas")
    public Vartotojas add(Vartotojas vartotojas) {
        return vartotojasService.save(vartotojas);
    }

    @DeleteMapping("/Vartotojas/{id}")
    public void deleteById(@PathVariable("id") int id) {

        try {
            vartotojasService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/Vartotojas")
    public void delete(Vartotojas vartotojas) {
        vartotojasService.delete(vartotojas);
    }

    @PutMapping("/Vartotojas/{id}")
    public Vartotojas replaceVartotojas(Vartotojas vartotojas, @PathVariable("id") int id) {
        return vartotojasService.replaceVartotojas(vartotojas, id);
    }
}
