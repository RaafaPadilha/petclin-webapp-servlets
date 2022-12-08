package br.com.raafapadilha.petclin.enumeration;

/**
 *
 * @author RaafaPadilha
 */
public enum AnimalType {

	DOG("Cachorro"), CAT("Gato");

	private final String value;

	private AnimalType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
