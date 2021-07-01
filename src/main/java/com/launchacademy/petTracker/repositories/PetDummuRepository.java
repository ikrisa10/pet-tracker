package com.launchacademy.petTracker.repositories;

import com.launchacademy.petTracker.models.PetDummy;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetDummuRepository extends PagingAndSortingRepository<PetDummy, Integer> {

}
