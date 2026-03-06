package com.galeria.gestion.artes.dto;

import com.galeria.gestion.artes.model.ArtLeasing;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ObraInventarioDTO {

    private String id;
    private String titulo;
    private int anio;
    private String estilo;
    private String nombreAutor;
    private double precio;
    private LocalDate fechaRegistro;
    private List<ArtLeasing> historialLeasing;
}
