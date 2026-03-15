package com.galeria.gestion.artes.controllers;

import com.galeria.gestion.artes.model.Hold;
import com.galeria.gestion.artes.services.HoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holds")
public class HoldController {

    @Autowired
    private HoldService holdService;

    @GetMapping
    public ResponseEntity<List<Hold>> getAllHolds()
    {
        return ResponseEntity.ok(holdService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hold> getHoldById(@PathVariable String id) {
        return holdService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hold> createHold(@RequestBody Hold hold)
    {
        Hold savedHold = holdService.save(hold);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHold);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHold(@PathVariable String id)
    {
        Optional<Hold> holdOpt = holdService.findById(id);
        if (holdOpt.isPresent()) {
            holdService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}