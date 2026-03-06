package com.galeria.gestion.artes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "artistas")
public class Artista {

    @Id
    private String id;
    private String nombre;
    private String nacionalidad;
    private LocalDate fechaRegistro;
    private String biografia;

    @Indexed(unique = true)
    private String email;
}
