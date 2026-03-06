package com.galeria.gestion.artes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtLeasing {

    private String cliente; // Ej: "Hotel Ritz", "Productora de Cine X"
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double cuotaMensual; // Beneficio pasivo que genera la obra
}