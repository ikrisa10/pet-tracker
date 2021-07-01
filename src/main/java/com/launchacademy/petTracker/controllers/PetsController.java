package com.launchacademy.petTracker.controllers;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.models.PetDummy;
import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.repositories.PetDummuRepository;
import com.launchacademy.petTracker.services.PetService;
import com.launchacademy.petTracker.services.SpeciesService;
import java.util.List;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping
public class PetsController {

  private PetService petService;
  private SpeciesService speciesService;
  private PetDummuRepository petDummuRepository;

  @Autowired
  public PetsController(PetService petService, SpeciesService speciesService,
      PetDummuRepository petDummuRepository) {
    this.petService = petService;
    this.speciesService = speciesService;
    this.petDummuRepository = petDummuRepository;
  }

  @GetMapping("/pets")
  public String getPetsIndex(Pageable pageable, Model model) {
    model.addAttribute("pets", petService.findAll(pageable));
    return "pets/index";
  }

  @GetMapping("/pets/new")
  public String createNewPet(@ModelAttribute PetDummy petDummy) {
    return "pets/new";
  }

  @PostMapping("/pets")
  public String saveCreatedPet(@ModelAttribute @Valid PetDummy petDummy,
      BindingResult bindingResult) {
    System.out.println(bindingResult.getFieldErrors());
    if (bindingResult.hasErrors()) {
      return "/pets/new";
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
      petService.save(newPet);
      return "redirect:/pets";
    }
  }

  @NoArgsConstructor
  private class UrlNotFoundException extends RuntimeException {
  }

  @ControllerAdvice
  private class UrlNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(UrlNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/species/{id}")
  public String getSpecies(@PathVariable Integer id, Model model) {
    Species requestedSpecies = speciesService.findById(id)
        .orElseThrow(() -> new UrlNotFoundException());
    ;
    model.addAttribute("species", requestedSpecies);
    return "/species/show";
  }

}
