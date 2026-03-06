package com.galeria.gestion.artes.repositories;

import com.galeria.gestion.artes.model.Artista;
import com.galeria.gestion.artes.model.Obra;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraRepository extends MongoRepository<Obra,String> {
    List<Obra> findByAutorOrderByFechaRegistroDesc(Artista autor);

}
