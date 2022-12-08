package br.com.raafapadilha.petclin.dao;

import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.enumeration.Gender;
import br.com.raafapadilha.petclin.model.Animal;
import br.com.raafapadilha.petclin.model.factory.AnimalFactory;
import br.com.raafapadilha.petclin.model.factory.concrete.ConcreteAnimalFactory;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RaafaPadilha
 */
public class AnimalDao extends Dao<Animal> {

	public AnimalDao() throws SQLException {
		super();
	}

	@Override
	public void create(Animal entity) throws SQLException {
		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"INSERT INTO animals("
				+ "	ani_name, "
				+ "	ani_breed, "
				+ "	ani_color, "
				+ "	ani_gender, "
				+ "	ani_birth_date, "
				+ "	ani_weigth, "
				+ "	ani_type) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);"
		)) {
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getBreed());
			stmt.setString(3, entity.getColor());
			stmt.setString(4, entity.getGender().toString());
			stmt.setDate(5, Date.valueOf(entity.getBirthDate()));
			stmt.setFloat(6, entity.getWeigth());
			stmt.setString(7, entity.getAnimalType().toString());

			stmt.executeUpdate();
		}
	}

	@Override
	public void update(Animal entity) throws SQLException {
		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"UPDATE animals "
				+ "SET"
				+ "	ani_name = ?, "
				+ "	ani_breed = ?, "
				+ "	ani_color = ?, "
				+ "	ani_gender = ?, "
				+ "	ani_birth_date = ?, "
				+ "	ani_weigth = ?, "
				+ "	ani_type = ? "
				+ "WHERE"
				+ "	ani_id = ?;"
		)) {
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getBreed());
			stmt.setString(3, entity.getColor());
			stmt.setString(4, entity.getGender().toString());
			stmt.setDate(5, Date.valueOf(entity.getBirthDate()));
			stmt.setFloat(6, entity.getWeigth());
			stmt.setString(7, entity.getAnimalType().toString());
			stmt.setLong(8, entity.getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public void delete(Animal entity) throws SQLException {
		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"DELETE FROM animals WHERE ani_id = ?;"
		)) {
			stmt.setLong(1, entity.getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public List<Animal> findAll() throws SQLException {
		List<Animal> animals = new ArrayList<>();

		try ( PreparedStatement stmt = getConnection().prepareStatement(
				"SELECT"
				+ "	a.ani_id idAnimal, "
				+ "	a.ani_name nameAnimal, "
				+ "	a.ani_breed breedAnimal, "
				+ "	a.ani_color colorAnimal, "
				+ "	a.ani_gender genderAnimal, "
				+ "	a.ani_birth_date birthDateAnimal, "
				+ "	a.ani_weigth weigthAnimal, "
				+ "	a.ani_type typeAnimal "
				+ "FROM"
				+ "	animals a "
				+ "ORDER BY a.ani_name;"
		);  ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				AnimalFactory animalFactory = new ConcreteAnimalFactory();
				Animal animal = animalFactory.getAnimal(
						AnimalType.valueOf(rs.getString("typeAnimal")));

				animal.setId(rs.getLong("idAnimal"));
				animal.setName(rs.getString("nameAnimal"));
				animal.setBreed(rs.getString("breedAnimal"));
				animal.setColor(rs.getString("colorAnimal"));
				animal.setGender(Gender.valueOf(rs.getString("genderAnimal")));
				animal.setBirthDate(LocalDate.parse(rs.getString("birthDateAnimal")));
				animal.setWeigth(rs.getFloat("weigthAnimal"));

				animals.add(animal);
			}
		}

		return animals;
	}

	@Override
	public Animal findById(Long id) throws SQLException {
		AnimalFactory animalFactory = new ConcreteAnimalFactory();
		Animal animal = null;
		PreparedStatement stmt = getConnection().prepareStatement(
				"SELECT"
				+ "	a.ani_id idAnimal, "
				+ "	a.ani_name nameAnimal, "
				+ "	a.ani_breed breedAnimal, "
				+ "	a.ani_color colorAnimal, "
				+ "	a.ani_gender genderAnimal, "
				+ "	a.ani_birth_date birthDateAnimal, "
				+ "	a.ani_weigth weigthAnimal, "
				+ "	a.ani_type typeAnimal "
				+ "FROM"
				+ "	animals a "
				+ "WHERE"
				+ "	ani_id = ?;"
		);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			animal = animalFactory.getAnimal(AnimalType.valueOf(rs.getString("typeAnimal")));
			animal.setId(rs.getLong("idAnimal"));
			animal.setName(rs.getString("nameAnimal"));
			animal.setBreed(rs.getString("breedAnimal"));
			animal.setColor(rs.getString("colorAnimal"));
			animal.setGender(Gender.valueOf(rs.getString("genderAnimal")));
			animal.setBirthDate(LocalDate.parse(rs.getString("birthDateAnimal")));
			animal.setWeigth(rs.getFloat("weigthAnimal"));
		}

		return animal;
	}
}
