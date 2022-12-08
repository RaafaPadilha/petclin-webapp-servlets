package br.com.raafapadilha.petclin.service;

import br.com.raafapadilha.petclin.dao.AnimalDao;
import br.com.raafapadilha.petclin.model.Animal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RaafaPadilha
 */
public class AnimalService {

	private final Logger LOGGER = LoggerFactory.getLogger(AnimalService.class);

	public List<Animal> findAllAnimals() throws SQLException {
		List<Animal> animals = new ArrayList<>();
		AnimalDao dao = null;

		try {
			dao = new AnimalDao();
			animals = dao.findAll();
		} catch (SQLException ex) {
			LOGGER.error("Error find all animals", ex);
		} finally {
			if (dao != null) {
				try {
					dao.closeConnection();
				} catch (SQLException ex) {
					LOGGER.warn("Error closing connection", ex);
				}
			}
		}

		return animals;
	}
}
