package com.launchacademy.petTracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "dummies")
@NoArgsConstructor
@Getter
@Setter
public class PetDummy {

  @Id
  @SequenceGenerator(name = "dummies_generator", sequenceName = "dummies_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dummies_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank(message = "Name field can't contain only spaces")
  @Length(min = 1, max = 19, message = "Name should be 1 to 19 characters")
  @NotNull(message = "Name should be present")
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank(message = "Species should be present")
  @Column(name = "species", nullable = false)
  private String species;

  @NotBlank(message = "Breed field can't contain only spaces")
  @Length(min = 1, max = 14, message = "Name should be 1 to 14 characters")
  @Column(name = "breed", nullable = false)
  @Pattern(regexp = "(\\D+)")
  private String breed;

  @Min(value = 1)
  @Max(value = 999)
  @NotNull(message = "Age should be selected")
  @Column(name = "age", nullable = false)
  private Integer age;

  @NotNull(message = "Neutered status should be selected")
  @Column(name = "neutered", nullable = false)
  private Boolean neutered;
}
