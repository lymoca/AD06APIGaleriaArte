package com.galeria.gestion.artes.controllers;

import com.galeria.gestion.artes.model.Artista;
import com.galeria.gestion.artes.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<Artista>> getAllArtistas()
    {
        return ResponseEntity.ok(artistaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable String id) {
        Optional<Artista> artistaOpt = artistaService.findById(id);
        if (artistaOpt.isPresent()) {
            return ResponseEntity.ok(artistaOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Artista> createArtista(@RequestBody Artista artista)
    {
        Artista savedArtista = artistaService.save(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> updateArtista(@PathVariable String id, @RequestBody Artista artista)
    {
        Optional<Artista> artistaOpt = artistaService.findById(id);
        if (artistaOpt.isPresent()) {
            artista.setId(id);
            return ResponseEntity.ok(artistaService.save(artista));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtista(@PathVariable String id)
    {
        Optional<Artista> artistaOpt = artistaService.findById(id);
        if (artistaOpt.isPresent()) {
            artistaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}