package com.gatcha.player.model;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "players")
public class Player {
    @MongoId @NotNull private String id;
    @NotNull private int level;
    @NotNull private int experience;
    @NotNull private List<String> monsters = new ArrayList<>();
}
