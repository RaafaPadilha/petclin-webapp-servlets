package br.com.raafapadilha.petclin.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author RaafaPadilha
 */
public class Appointment {

	private Long id;
	private LocalDate dateAppointment;
	private LocalTime timeAppointment;
	private String description;
	private Animal animal;

	public Appointment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(LocalDate dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public LocalTime getTimeAppointment() {
		return timeAppointment;
	}

	public void setTimeAppointment(LocalTime timeAppointment) {
		this.timeAppointment = timeAppointment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
