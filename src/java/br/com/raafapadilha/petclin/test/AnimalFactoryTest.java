package br.com.raafapadilha.petclin.test;

import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.model.Animal;
import br.com.raafapadilha.petclin.model.factory.AnimalFactory;
import br.com.raafapadilha.petclin.model.factory.concrete.ConcreteAnimalFactory;

/**
 *
 * @author RaafaPadilha
 */
public class AnimalFactoryTest {

	public static void main(String[] args) {
		AnimalFactory factory = new ConcreteAnimalFactory();

		Animal cat = factory.getAnimal(AnimalType.CAT);
		System.out.println(cat.getClass());

		Animal dog = factory.getAnimal(AnimalType.DOG);
		System.out.println(dog.getClass());
	}
}
