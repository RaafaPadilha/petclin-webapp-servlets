package br.com.raafapadilha.petclin.dao;

import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.enumeration.Gender;
import br.com.raafapadilha.petclin.model.Animal;
import br.com.raafapadilha.petclin.model.Appointment;
import br.com.raafapadilha.petclin.model.factory.AnimalFactory;
import br.com.raafapadilha.petclin.model.factory.concrete.ConcreteAnimalFactory;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RaafaPadilha
 */
public class AppointmentDao extends Dao<Appointment> {

	public AppointmentDao() throws SQLException {
		super();
	}

	@Override
	public void create(Appointment entity) throws SQLException {
		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"INSERT INTO appointments("
				+ "	app_day, "
				+ "	app_time, "
				+ "	app_description, "
				+ "	app_animal_id) "
				+ "VALUES (?, ?, ?, ?);"
		)) {
			stmt.setDate(1, Date.valueOf(entity.getDateAppointment()));
			stmt.setTime(2, Time.valueOf(entity.getTimeAppointment()));
			stmt.setString(3, entity.getDescription());
			stmt.setLong(4, entity.getAnimal().getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public void update(Appointment entity) throws SQLException {
		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"UPDATE appointments SET"
				+ "	app_day = ?, "
				+ "	app_time = ?, "
				+ "	app_description = ?, "
				+ "	app_animal_id = ? "
				+ "WHERE"
				+ "	app_id = ?;"
		)) {
			stmt.setDate(1, Date.valueOf(entity.getDateAppointment()));
			stmt.setTime(2, Time.valueOf(entity.getTimeAppointment()));
			stmt.setString(3, entity.getDescription());
			stmt.setLong(4, entity.getAnimal().getId());
			stmt.setLong(5, entity.getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public void delete(Appointment entity) throws SQLException {
		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"DELETE FROM appointments WHERE app_id = ?;"
		)) {
			stmt.setLong(1, entity.getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public List<Appointment> findAll() throws SQLException {
		AnimalFactory animalFactory = new ConcreteAnimalFactory();
		List<Appointment> appointments = new ArrayList<>();

		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"SELECT"
				+ " app.app_id idAppointment, "
				+ "	app.app_day dateAppointment, "
				+ "	app.app_time timeAppointment, "
				+ "	app.app_description descriptionAppointment, "
				+ "	ani.ani_id idAnimal, "
				+ "	ani.ani_name nameAnimal, "
				+ "	ani.ani_breed breedAnimal, "
				+ "	ani.ani_color colorAnimal, "
				+ "	ani.ani_gender genderAnimal, "
				+ "	ani.ani_birth_date birthDateAnimal, "
				+ " ani.ani_weigth weigthAnimal, "
				+ " ani.ani_type typeAnimal "
				+ "FROM"
				+ "	appointments app, "
				+ "	animals ani "
				+ "WHERE"
				+ "	app.app_animal_id = ani.ani_id "
				+ "ORDER BY app.app_day, ani.ani_name;"
		);  ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Appointment appointment = new Appointment();
				Animal animal = animalFactory.getAnimal(
						AnimalType.valueOf(rs.getString("typeAnimal")));

				appointment.setId(rs.getLong("idAppointment"));
				appointment.setDateAppointment(LocalDate.parse(rs.getString("dateAppointment")));
				appointment.setTimeAppointment(LocalTime.parse(rs.getString("timeAppointment")));
				appointment.setDescription(rs.getString("descriptionAppointment"));
				appointment.setAnimal(animal);

				animal.setId(rs.getLong("idAnimal"));
				animal.setName(rs.getString("nameAnimal"));
				animal.setBreed(rs.getString("breedAnimal"));
				animal.setColor(rs.getString("colorAnimal"));
				animal.setGender(Gender.valueOf(rs.getString("genderAnimal")));
				animal.setBirthDate(LocalDate.parse(rs.getString("birthDateAnimal")));
				animal.setWeigth(rs.getFloat("weigthAnimal"));

				appointments.add(appointment);
			}
		}

		return appointments;
	}

	@Override
	public Appointment findById(Long id) throws SQLException {
		Appointment appointment = null;

		PreparedStatement stmt = getConnection().prepareStatement(
				"SELECT"
				+ "	app.app_id idAppointment, "
				+ "	app.app_day dateAppointment, "
				+ "	app.app_time timeAppointment, "
				+ "	app.app_description descriptionAppointment, "
				+ " ani.ani_id idAnimal, "
				+ " ani.ani_name nameAnimal, "
				+ " ani.ani_breed breedAnimal, "
				+ " ani.ani_color colorAnimal, "
				+ " ani.ani_gender genderAnimal, "
				+ " ani.ani_birth_date birthDateAnimal, "
				+ " ani.ani_weigth weigthAnimal, "
				+ " ani.ani_type typeAnimal "
				+ "FROM"
				+ "	appointments app, "
				+ "	animals ani "
				+ "WHERE"
				+ "	app.app_id = ? AND "
				+ "	app.app_animal_id = ani.ani_id;"
		);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			appointment = new Appointment();
			AnimalFactory animalFactory = new ConcreteAnimalFactory();
			Animal animal = animalFactory.getAnimal(
					AnimalType.valueOf(rs.getString("typeAnimal")));

			appointment.setId(rs.getLong("idAppointment"));
			appointment.setDateAppointment(LocalDate.parse(rs.getString("dateAppointment")));
			appointment.setTimeAppointment(LocalTime.parse(rs.getString("timeAppointment")));
			appointment.setDescription(rs.getString("descriptionAppointment"));
			appointment.setAnimal(animal);

			animal.setId(rs.getLong("idAnimal"));
			animal.setName(rs.getString("nameAnimal"));
			animal.setBreed(rs.getString("breedAnimal"));
			animal.setColor(rs.getString("colorAnimal"));
			animal.setGender(Gender.valueOf(rs.getString("genderAnimal")));
			animal.setBirthDate(LocalDate.parse(rs.getString("birthDateAnimal")));
			animal.setWeigth(rs.getFloat("weigthAnimal"));
		}

		return appointment;
	}
}
