package com.galeria.gestion.artes.repositories;

import com.galeria.gestion.artes.model.Hold;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldRepository extends MongoRepository<Hold, String> {

}
