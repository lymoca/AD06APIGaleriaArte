package com.galeria.gestion.artes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "holds")
public class Hold {

    @Id
    private String id;

    private String coleccionista;
    private String canal;

    @DBRef
    private Obra obraReservada;

    // ÍNDICE TTL: La retención se destruye automáticamente a las 48 horas
    @Indexed(expireAfter = "48h")
    private Date fechaRetencion;
}
