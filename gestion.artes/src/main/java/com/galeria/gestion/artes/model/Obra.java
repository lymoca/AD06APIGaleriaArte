package com.galeria.gestion.artes.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "obras")
public class Obra {

    @Id
    private String id;
    private String titulo;
    private Integer anio;
    private LocalDate fechaRegistro;
    private Double precio;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String estilo;

    private List<ArtLeasing> historialAlquiler;

    @DBRef
    private Artista autor;



}
