package com.launchacademy.petTracker.seeders;

import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.repositories.SpeciesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpeciesSeeder implements CommandLineRunner {

  private SpeciesRepository speciesRepository;

  @Autowired
  public SpeciesSeeder(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Species speciesOne = new Species();
    speciesOne.setSpeciesName("Cat");

    Species speciesTwo = new Species();
    speciesTwo.setSpeciesName("Dyno");

    if (((List<Species>) speciesRepository.findAll()).size() == 0) {
      speciesRepository.save(speciesOne);
      speciesRepository.save(speciesTwo);
    }
  }
}
