package com.gatcha.monstre.service;

import com.gatcha.monstre.model.Monstre;
import com.gatcha.monstre.repository.MonstreRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonstreService {

  @Autowired
  private MonstreRepository monstreRepository;

  public List<Monstre> getAllMonstres() {
    return monstreRepository.findAll();
  }

  public Optional<Monstre> getMonstre(String id) {
    return monstreRepository.findById(id);
  }

  public Monstre ajouterMonstre(Monstre monstre) {
    return monstreRepository.save(monstre);
  }

  public boolean supprimerMonstre(String id) {
    if (monstreRepository.existsById(id)) {
      monstreRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public Optional<Monstre> getMonstreById(String id) {
    return monstreRepository.findById(id);
  }
}
