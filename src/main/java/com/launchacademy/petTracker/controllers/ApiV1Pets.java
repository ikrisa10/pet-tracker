package com.launchacademy.petTracker.controllers;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.models.PetDummy;
import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.repositories.PetRepository;
import com.launchacademy.petTracker.services.PetService;
import com.launchacademy.petTracker.services.SpeciesService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Pets {

  private PetService petService;
  private PetRepository petRepository;
  private SpeciesService speciesService;

  @Autowired
  public ApiV1Pets(PetService petService, PetRepository petRepository,
      SpeciesService speciesService) {
    this.petService = petService;
    this.petRepository = petRepository;
    this.speciesService = speciesService;
  }

  @GetMapping("/pets")
  public Page<Pet> getAllPets(Pageable pageable) {
    return petService.findAll(pageable);
  }

  @PostMapping("/pets")
  public ResponseEntity create(@Valid @RequestBody PetDummy petDummy, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      Pet newPet = new Pet();
      newPet.setName(petDummy.getName());
      newPet.setBreed(petDummy.getBreed());
      newPet.setAge(petDummy.getAge());
      newPet.setNeutered(petDummy.getNeutered());

      List<Species> existingSpecies = speciesService.findBySpeciesName(petDummy.getSpecies());
      if (existingSpecies.size() == 0) {
        Species species = new Species();
        species.setSpeciesName(petDummy.getSpecies());
        speciesService.save(species);
        newPet.setSpecies(speciesService.findALl().get(speciesService.findALl().size() - 1));
      } else {
        newPet.setSpecies(existingSpecies.get(0));
      }

      return new ResponseEntity<Pet>(petRepository.save(newPet), HttpStatus.CREATED);
    }
  }
}

