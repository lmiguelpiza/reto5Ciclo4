package com.ciclo4.reto.interfaces;

import com.ciclo4.reto.modelo.Supplements;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InterfaceSupplements extends MongoRepository <Supplements, String>{
    public List<Supplements> findByPriceLessThanEqual(double precio);
    //Reto 5
    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<Supplements> findByDescriptionLike(String description);
}
