package br.com.raafapadilha.petclin.enumeration;

/**
 *
 * @author RaafaPadilha
 */
public enum Gender {

	MALE("Macho"), FEMALE("Fêmea");

	private final String value;

	private Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
