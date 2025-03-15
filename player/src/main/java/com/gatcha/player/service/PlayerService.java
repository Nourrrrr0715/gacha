package com.gatcha.player.service;

import com.gatcha.player.model.Player;
import com.gatcha.player.repository.PlayerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    public static final String PLAYER_NOT_FOUND = "Player not found with the ID: %s";
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(String id) {
        return playerRepository.findById(id);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public String deletePlayer(String id) {
        return getPlayerById(id)
                .map(
                        player -> {
                            playerRepository.delete(player);
                            return player.getId();
                        })
                .orElseThrow(() -> new RuntimeException(PLAYER_NOT_FOUND.formatted(id)));
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }

    public Player updatePlayer(String id, Player playerDetails) {
        return playerRepository
                .findById(id)
                .map(
                        player -> {
                            player.setLevel(playerDetails.getLevel());
                            player.setExperience(playerDetails.getExperience());
                            player.setMonsters(playerDetails.getMonsters());

                            return playerRepository.save(player);
                        })
                .orElseThrow(() -> new RuntimeException(PLAYER_NOT_FOUND.formatted(id)));
    }
}
