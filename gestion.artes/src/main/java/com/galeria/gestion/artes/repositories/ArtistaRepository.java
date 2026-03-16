package com.galeria.gestion.artes.repositories;

import com.galeria.gestion.artes.model.Artista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistaRepository extends MongoRepository<Artista,String> {

}
