package br.com.raafapadilha.petclin.service;

import br.com.raafapadilha.petclin.dao.AppointmentDao;
import br.com.raafapadilha.petclin.model.Appointment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RaafaPadilha
 */
public class AppointmentService {

	private final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);

	public List<Appointment> findAllAppointments() throws SQLException {
		List<Appointment> appointments = new ArrayList<>();
		AppointmentDao appointmentDao = null;

		try {
			appointmentDao = new AppointmentDao();
			appointments = appointmentDao.findAll();
		} catch (SQLException ex) {
			LOGGER.error("Error find all appointments", ex);
		} finally {
			if (appointmentDao != null) {
				try {
					appointmentDao.closeConnection();
				} catch (SQLException ex) {
					LOGGER.warn("Error closing connection", ex);
				}
			}
		}

		return appointments;
	}
}
