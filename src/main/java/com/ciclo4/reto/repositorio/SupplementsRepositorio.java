package com.ciclo4.reto.repositorio;


import com.ciclo4.reto.interfaces.InterfaceSupplements;
import com.ciclo4.reto.modelo.Supplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplementsRepositorio {
    @Autowired
    private InterfaceSupplements repository;

    public List<Supplements> getAll() {
        return repository.findAll();
    }

    public Optional<Supplements> getsupplements(String reference) {
        return repository.findById(reference);
    }

    public Supplements create(Supplements clothe) {
        return repository.save(clothe);
    }

    public void update(Supplements clothe) {
        repository.save(clothe);
    }

    public void delete(Supplements clothe) {
        repository.delete(clothe);
    }
    public List<Supplements> findByPriceLessThanEqual(double precio) {
        return repository.findByPriceLessThanEqual(precio);
    }

    public List<Supplements> findByDescriptionLike(String description) {
        return repository.findByDescriptionLike(description);
    }
}
