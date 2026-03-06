package com.galeria.gestion.artes.services;

import com.galeria.gestion.artes.model.Hold;
import com.galeria.gestion.artes.repositories.HoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoldService {

    @Autowired
    private HoldRepository holdRepository;

    public List<Hold> findAll()
    {
        return holdRepository.findAll();
    }

    public Optional<Hold> findById(String id)
    {
        return holdRepository.findById(id);
    }

    public Hold save(Hold hold)
    {
        if (hold.getFechaRetencion() == null) {
            hold.setFechaRetencion(new Date());
        }
        return holdRepository.save(hold);
    }

    public void deleteById(String id)
    {
        holdRepository.deleteById(id);
    }
}