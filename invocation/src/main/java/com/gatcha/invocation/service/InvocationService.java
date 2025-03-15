package com.example.gatcha.service;

import com.example.gatcha.model.InvocationBuffer;
import com.example.gatcha.model.Monstre;
import com.example.gatcha.repository.InvocationBufferRepository;
import com.gatcha.invocation.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class InvocationService {

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private InvocationBufferRepository invocationBufferRepository;

    @Autowired
    private RestTemplate restTemplate; // Pour communiquer avec l'API Monstre

    private static final String MONSTER_API_URL = "http://localhost:8080/api/monsters";

    // Génère un monstre aléatoire en fonction des taux d'invocation
    public Monster invokeMonster() {
        List<Monster> monsters = monsterRepository.findAllByOrderByInvocationRateDesc();
        double totalRate = monsters.stream().mapToDouble(Monster::getInvocationRate).sum();
        double randomValue = new Random().nextDouble() * totalRate;

        double cumulativeRate = 0.0;
        for (Monster monster : monsters) {
            cumulativeRate += monster.getInvocationRate();
            if (randomValue <= cumulativeRate) {
                return monster;
            }
        }
        return null; // En cas d'erreur
    }

    // Enregistre l'invocation dans le tampon et envoie le monstre à l'API Monstre
    public String processInvocation(String playerId) {
        Monster monster = invokeMonster();
        if (monster == null) {
            throw new RuntimeException("Failed to generate a monster.");
        }

        // Enregistrer le monstre dans l'API Monstre
        Monster createdMonster = restTemplate.postForObject(MONSTER_API_URL, monster, Monster.class);
        if (createdMonster == null) {
            throw new RuntimeException("Failed to create monster in Monster API.");
        }

        // Enregistrer l'invocation dans le tampon
        InvocationBuffer buffer = new InvocationBuffer();
        buffer.setMonsterId(createdMonster.getId());
        buffer.setPlayerId(playerId);
        buffer.setProcessed(false);
        invocationBufferRepository.save(buffer);

        // Ajouter le monstre au joueur (via l'API Joueur)
        // Ici, vous pouvez appeler l'API Joueur pour ajouter le monstre à la liste du joueur

        // Marquer l'invocation comme traitée
        buffer.setProcessed(true);
        invocationBufferRepository.save(buffer);

        return createdMonster.getId();
    }
}