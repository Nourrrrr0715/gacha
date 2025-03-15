package com.gatcha.monstre.controller;

import com.gatcha.monstre.model.Monstre;
import com.gatcha.monstre.service.MonstreService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monstres")
public class MonstreController {

  private final MonstreService monstreService;

  public MonstreController(MonstreService monstreService) {
    this.monstreService = monstreService;
  }

  @GetMapping
  public ResponseEntity<List<Monstre>> getAllMonstres() {
    List<Monstre> monstres = monstreService.getAllMonstres();
    return ResponseEntity.ok(monstres);
  }

  @PostMapping
  public ResponseEntity<Monstre> ajouterMonstre(@RequestBody Monstre monstre) {
    Monstre nouveauMonstre = monstreService.ajouterMonstre(monstre);
    return ResponseEntity.ok(nouveauMonstre);
  }
}
