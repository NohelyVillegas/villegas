package com.examen.villegas.repository;

import com.examen.villegas.model.TurnoCaja;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoCajaRepository extends MongoRepository<TurnoCaja, String> {
    
} 