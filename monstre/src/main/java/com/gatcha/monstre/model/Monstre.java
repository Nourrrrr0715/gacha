package com.gatcha.monstre.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor // Génère un constructeur avec tous les attributs
@NoArgsConstructor // Génère un constructeur vide
@Document(collection = "monstres") // Collection MongoDB

public class Monstre {
  @MongoId private String id;
  private String nom;
  private String typeElementaire; // Ex: Feu, Eau, Vent
  private int hp; // Points de vie
  private int atk; // Attaque
  private int def; // Défense
  private int vit; // Vitesse
  private int niveau = 1;
  private int experience = 0;
  private List<String> competences = new ArrayList<>();
}
