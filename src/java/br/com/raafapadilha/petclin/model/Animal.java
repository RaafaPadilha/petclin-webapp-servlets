package br.com.raafapadilha.petclin.model;

import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.enumeration.Gender;
import java.time.LocalDate;

/**
 *
 * @author RaafaPadilha
 */
public abstract class Animal {

	private Long id;
	private String name;
	private String breed;
	private String color;
	private Gender gender;
	private LocalDate birthDate;
	private Float weigth;
	private final AnimalType animalType;

	public Animal(AnimalType animalType) {
		this.animalType = animalType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Float getWeigth() {
		return weigth;
	}

	public void setWeigth(Float weigth) {
		this.weigth = weigth;
	}

	public AnimalType getAnimalType() {
		return animalType;
	}
}
