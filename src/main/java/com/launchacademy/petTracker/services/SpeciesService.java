package com.launchacademy.petTracker.services;

import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.repositories.SpeciesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService {
  private SpeciesRepository speciesRepository;

  public SpeciesService (SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  public List<Species> findBySpeciesName(String name) {
    return this.speciesRepository.findAllBySpeciesNameIgnoreCase(name);
  }

  public void save(Species species) {
    this.speciesRepository.save(species);
  }

  public List<Species> findALl() {
    return (List<Species>) this.speciesRepository.findAll();
  }

  public Optional<Species> findById(Integer id) {
   return this.speciesRepository.findById(id);
  }
}
