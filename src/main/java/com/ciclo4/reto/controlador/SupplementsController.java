package com.ciclo4.reto.controlador;

import com.ciclo4.reto.modelo.Supplements;
import com.ciclo4.reto.servicio.SupplementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplements")
@CrossOrigin("*")

public class SupplementsController {
    @Autowired
    private SupplementsService supplementsService;

    @GetMapping("/all")
    public List<Supplements> getAll() {
        return supplementsService.getAll();
    }

    @GetMapping("/{reference}")
    public Optional<Supplements> getsupplements(@PathVariable("reference") String reference) {
        return supplementsService.getsupplements(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplements create(@RequestBody Supplements gadget) {
        return supplementsService.create(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplements update(@RequestBody Supplements gadget) {
        return supplementsService.update(gadget);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference) {
        return supplementsService.delete(reference);
    }
    @GetMapping("/price/{price}")
    public List<Supplements> findByPriceLessThanEqual(@PathVariable("price") double precio) {
        return supplementsService.findByPriceLessThanEqual(precio);
    }

    @GetMapping("/description/{description}")
    public List<Supplements> findByDescriptionLike(@PathVariable("description") String description) {
        return supplementsService.findByDescriptionLike(description);
    }
}

