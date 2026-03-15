package com.galeria.gestion.artes.controllers;

import com.galeria.gestion.artes.dto.ObraFichaTecnicaDTO;
import com.galeria.gestion.artes.dto.ObraInventarioDTO;
import com.galeria.gestion.artes.model.ArtLeasing;
import com.galeria.gestion.artes.model.Obra;
import com.galeria.gestion.artes.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @GetMapping
    public ResponseEntity<List<Obra>> getAllObras()
    {
        return ResponseEntity.ok(obraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> getObraById(@PathVariable String id)
    {
        Optional<Obra> obraOpt = obraService.findById(id);
        if (obraOpt.isPresent()) {
            return ResponseEntity.ok(obraOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/catalogo")
    public ResponseEntity<List<ObraFichaTecnicaDTO>> getCatalogoPublico() {
        return ResponseEntity.ok(obraService.obtenerCatalogo());
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<ObraInventarioDTO>> getInventarioInterno() {
        return ResponseEntity.ok(obraService.obtenerInventario());
    }

    @PostMapping
    public ResponseEntity<Obra> createObra(@RequestBody Obra obra)
    {
        Obra savedObra = obraService.save(obra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedObra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> updateObra(@PathVariable String id, @RequestBody Obra obra)
    {
        Optional<Obra> obraOpt = obraService.findById(id);
        if (obraOpt.isPresent()) {
            obra.setId(id);
            return ResponseEntity.ok(obraService.save(obra));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObra(@PathVariable String id)
    {
        Optional<Obra> obraOpt = obraService.findById(id);
        if (obraOpt.isPresent()) {
            obraService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/leasing")
    public ResponseEntity<Void> addLeasing(@PathVariable String id, @RequestBody ArtLeasing leasing)
    {
        boolean result = obraService.addLeasing(id, leasing);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/leasing/{cliente}")
    public ResponseEntity<Void> removeLeasing(@PathVariable String id, @PathVariable String cliente)
    {
        boolean result = obraService.removeLeasing(id, cliente);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}