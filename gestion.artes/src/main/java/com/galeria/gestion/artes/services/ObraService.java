package com.galeria.gestion.artes.services;

import com.galeria.gestion.artes.dto.ObraFichaTecnicaDTO;
import com.galeria.gestion.artes.dto.ObraInventarioDTO;
import com.galeria.gestion.artes.model.ArtLeasing;
import com.galeria.gestion.artes.model.Obra;
import com.galeria.gestion.artes.repositories.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    public List<Obra> findAll()
    {
        return obraRepository.findAll();
    }

    public Optional<Obra> findById(String id)
    {
        return obraRepository.findById(id);
    }

    public Obra save(Obra obra)
    {
        return obraRepository.save(obra);
    }

    public void deleteById(String id)
    {
        obraRepository.deleteById(id);
    }

    public boolean addLeasing(String obraId, ArtLeasing leasing)
    {
        Optional<Obra> obraOpt = obraRepository.findById(obraId);
        if (obraOpt.isPresent()) {
            Obra obra = obraOpt.get();
            if (obra.getHistorialAlquiler() == null) {
                obra.setHistorialAlquiler(new ArrayList<>());
            }
            obra.getHistorialAlquiler().add(leasing);
            obraRepository.save(obra);
            return true;
        }
        return false;
    }

    public boolean removeLeasing(String obraId, String cliente)
    {
        Optional<Obra> obraOpt = obraRepository.findById(obraId);
        if (obraOpt.isPresent()) {
            Obra obra = obraOpt.get();
            if (obra.getHistorialAlquiler() != null) {
                boolean removed = obra.getHistorialAlquiler().removeIf(l -> l.getCliente().equals(cliente));
                if (removed) {
                    obraRepository.save(obra);
                    return true;
                }
            }
        }
        return false;
    }

    public List<ObraFichaTecnicaDTO> obtenerCatalogo() {
        return obraRepository.findAll().stream().map(obra -> {
            ObraFichaTecnicaDTO dto = new ObraFichaTecnicaDTO();
            dto.setId(obra.getId());
            dto.setTitulo(obra.getTitulo());
            dto.setAnio(obra.getAnio());
            dto.setEstilo(obra.getEstilo());

            if (obra.getAutor() != null) {
                dto.setNombreAutor(obra.getAutor().getNombre());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ObraInventarioDTO> obtenerInventario() {
        return obraRepository.findAll().stream().map(obra -> {
            ObraInventarioDTO dto = new ObraInventarioDTO();
            dto.setId(obra.getId());
            dto.setTitulo(obra.getTitulo());
            dto.setAnio(obra.getAnio());
            dto.setPrecio(obra.getPrecio());
            dto.setEstilo(obra.getEstilo());
            dto.setHistorialLeasing(obra.getHistorialAlquiler());
            if (obra.getAutor() != null) {
                dto.setNombreAutor(obra.getAutor().getNombre());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}