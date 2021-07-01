package com.launchacademy.petTracker.services;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {
  private PetRepository petRepository;

  @Autowired
  public PetService (PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  public Page<Pet> findAll (Pageable pageable) {
    return this.petRepository.findAll(pageable);
  }

  public void save(Pet pet) {
    this.petRepository.save(pet);
  }
}
