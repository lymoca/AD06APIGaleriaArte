package com.galeria.gestion.artes.services;

import com.galeria.gestion.artes.model.Artista;
import com.galeria.gestion.artes.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> findAll()
    {

        return artistaRepository.findAll();
    }

    public Optional<Artista> findById(String id)
    {
        return artistaRepository.findById(id);
    }

    public Artista save(Artista artista)
    {
        return artistaRepository.save(artista);
    }

    public void deleteById(String id)
    {
        artistaRepository.deleteById(id);
    }
}