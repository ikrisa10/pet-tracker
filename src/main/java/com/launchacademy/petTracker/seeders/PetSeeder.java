package com.launchacademy.petTracker.seeders;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.repositories.PetRepository;
import com.launchacademy.petTracker.repositories.SpeciesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PetSeeder implements CommandLineRunner {

  private PetRepository petRepository;
  private SpeciesRepository speciesRepository;

  @Autowired
  public PetSeeder(PetRepository petRepository, SpeciesRepository speciesRepository) {
    this.petRepository = petRepository;
    this.speciesRepository = speciesRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    SpeciesSeeder speciesSeeder = new SpeciesSeeder(speciesRepository);
    speciesSeeder.run();

    Pet petOne = new Pet();
    petOne.setName("Boo");
    petOne.setBreed("Regular");
    petOne.setNeutered(false);
    petOne.setAge(10);
    petOne.setSpecies(speciesRepository.findById(1).get());

    Pet petTwo = new Pet();
    petTwo.setName("Fluff");
    petTwo.setBreed("Regular");
    petTwo.setNeutered(true);
    petTwo.setAge(5);
    petTwo.setSpecies(speciesRepository.findById(1).get());

    Pet petThree = new Pet();
    petThree.setName("Raptor");
    petThree.setBreed("Regular");
    petThree.setNeutered(true);
    petThree.setAge(50);
    petThree.setSpecies(speciesRepository.findById(2).get());

    if (((List<Pet>) petRepository.findAll()).size() == 0) {
      petRepository.save(petOne);
      petRepository.save(petTwo);
      petRepository.save(petThree);
    }
  }
}
