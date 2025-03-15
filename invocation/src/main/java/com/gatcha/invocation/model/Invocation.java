package com.example.gatcha.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "invocation_buffer")
public class InvocationBuffer {
    @Id
    private String id;
    private String monsterId; // ID du monstre généré
    private String playerId;  // ID du joueur qui invoque le monstre
    private boolean processed; // Indique si l'invocation a été traitée avec succès
}