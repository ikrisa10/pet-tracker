package com.launchacademy.petTracker.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "species")
public class Species {

  @Id
  @SequenceGenerator(name = "species_generator", sequenceName = "species_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "species_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @Column(name = "species_name", nullable = false)
  private String speciesName;

  @OneToMany(mappedBy = "species")
  private List<Pet> pets;

}
