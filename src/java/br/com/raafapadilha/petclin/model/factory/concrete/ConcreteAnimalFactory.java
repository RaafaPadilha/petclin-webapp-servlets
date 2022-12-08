package br.com.raafapadilha.petclin.model.factory.concrete;

import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.model.Animal;
import br.com.raafapadilha.petclin.model.Cat;
import br.com.raafapadilha.petclin.model.Dog;
import br.com.raafapadilha.petclin.model.factory.AnimalFactory;

/**
 *
 * @author RaafaPadilha
 */
public class ConcreteAnimalFactory extends AnimalFactory {

	@Override
	public Animal getAnimal(AnimalType animalType) {
		if (animalType == AnimalType.DOG) {
			return new Dog(animalType);
		}

		return new Cat(animalType);
	}
}
