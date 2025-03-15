package com.gatcha.player.controller;

import com.gatcha.player.model.Player;
import com.gatcha.player.service.PlayerService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearer-key",
        description = "authorization with JWT token", scheme = "bearer",
        bearerFormat = "JWT")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable String id) {
        return ResponseEntity.of(playerService.getPlayerById(id));
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody @Valid Player player) {
        return ResponseEntity.ok(playerService.createPlayer(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        return ResponseEntity.ok(playerService.deletePlayer(id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllPlayers() {
        playerService.deleteAllPlayers();
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable String id, @RequestBody @Valid Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }
}
