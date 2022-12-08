package br.com.raafapadilha.petclin.model.factory;

import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.model.Animal;

/**
 *
 * @author RaafaPadilha
 */
public abstract class AnimalFactory {

	public abstract Animal getAnimal(AnimalType animalType);
}
