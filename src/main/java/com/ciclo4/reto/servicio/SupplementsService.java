package com.ciclo4.reto.servicio;

import com.ciclo4.reto.modelo.Supplements;
import com.ciclo4.reto.repositorio.SupplementsRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplementsService {
    @Autowired
    private SupplementsRepositorio supplementsRepository;

    public List<Supplements> getAll() {
        return supplementsRepository.getAll();
    }

    public Optional<Supplements> getsupplements(String reference) {
        return supplementsRepository.getsupplements(reference);
    }

    public Supplements create(Supplements accesory) {
        if (accesory.getReference() == null) {
            return accesory;
        } else {
            return supplementsRepository.create(accesory);
        }
    }

    public Supplements update(Supplements supplements) {

        if (supplements.getReference() != null) {
            Optional<Supplements> supplementDb = supplementsRepository.getsupplements(supplements.getReference());
            if (!supplementDb.isEmpty()) {
                if (supplements.getBrand() != null) {
                    supplementDb.get().setBrand(supplements.getBrand());
                }
                if (supplements.getCategory() != null) {
                    supplementDb.get().setCategory(supplements.getCategory());
                }

                if (supplements.getDescription() != null) {
                    supplementDb.get().setDescription(supplements.getDescription());
                }
                if (supplements.getPrice() != 0.0) {
                    supplementDb.get().setPrice(supplements.getPrice());
                }
                if (supplements.getQuantity() != 0) {
                    supplementDb.get().setQuantity(supplements.getQuantity());
                }
                if (supplements.getPhotography() != null) {
                    supplementDb.get().setPhotography(supplements.getPhotography());
                }
                supplementDb.get().setAvailability(supplements.isAvailability());
                supplementsRepository.update(supplementDb.get());
                return supplementDb.get();
            } else {
                return supplements;
            }
        } else {
            return supplements;
        }
    }


    public boolean delete(String reference) {
        return getsupplements(reference).map(accesory -> {
            supplementsRepository.delete(accesory);
            return true;
        }).orElse(false);
    }

    public List<Supplements>findByPriceLessThanEqual(double price) {
        return supplementsRepository.findByPriceLessThanEqual(price);
    }

    public List<Supplements> findByDescriptionLike(String description) {
        return supplementsRepository.findByDescriptionLike(description);
    }

}
