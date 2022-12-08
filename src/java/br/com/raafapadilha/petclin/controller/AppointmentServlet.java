package br.com.raafapadilha.petclin.controller;

import br.com.raafapadilha.petclin.dao.AnimalDao;
import br.com.raafapadilha.petclin.dao.AppointmentDao;
import br.com.raafapadilha.petclin.model.Animal;
import br.com.raafapadilha.petclin.model.Appointment;
import br.com.raafapadilha.petclin.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RaafaPadilha
 */
@WebServlet(name = "AppointmentServlet", urlPatterns = {"/appointment"})
public class AppointmentServlet extends HttpServlet {

	private final Logger LOGGER = LoggerFactory.getLogger(AppointmentServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnimalDao animalDao = null;
		AppointmentDao appointmentDao = null;
		RequestDispatcher dispatcher = null;
		String action = request.getParameter("action");

		try {
			animalDao = new AnimalDao();
			appointmentDao = new AppointmentDao();

			switch (action) {
				case "insert": {
					LocalDate dateAppointment = Util.getDate(request.getParameter("dateAppointment"));
					LocalTime timeAppointment = LocalTime.parse(request.getParameter("timeAppointment"));
					String description = request.getParameter("description");
					Long animalId = Long.valueOf(request.getParameter("animalId"));

					Animal animal = animalDao.findById(animalId);

					Appointment appointment = new Appointment();
					appointment.setDateAppointment(dateAppointment);
					appointment.setTimeAppointment(timeAppointment);
					appointment.setDescription(description);
					appointment.setAnimal(animal);

					appointmentDao.create(appointment);
					dispatcher = request.getRequestDispatcher("templates/appointments/list.jsp");
					break;
				}

				case "update": {
					Long id = Long.valueOf(request.getParameter("id"));
					LocalDate dateAppointment = Util.getDate(request.getParameter("dateAppointment"));
					LocalTime timeAppointment = LocalTime.parse(request.getParameter("timeAppointment"));
					String description = request.getParameter("description");
					Long animalId = Long.valueOf(request.getParameter("animalId"));

					Animal animal = animalDao.findById(animalId);

					Appointment appointment = new Appointment();
					appointment.setId(id);
					appointment.setDateAppointment(dateAppointment);
					appointment.setTimeAppointment(timeAppointment);
					appointment.setDescription(description);
					appointment.setAnimal(animal);

					appointmentDao.update(appointment);
					dispatcher = request.getRequestDispatcher("templates/appointments/list.jsp");
					break;
				}

				case "delete": {
					Long id = Long.valueOf(request.getParameter("id"));

					Appointment appointment = new Appointment();
					appointment.setId(id);

					appointmentDao.delete(appointment);
					dispatcher = request.getRequestDispatcher("templates/appointments/list.jsp");
					break;
				}

				default: {
					Long id = Long.valueOf(request.getParameter("id"));
					Appointment appointment = appointmentDao.findById(id);
					request.setAttribute("appointment", appointment);

					if (action.equals("updateAppointment")) {
						dispatcher = request.getRequestDispatcher(
								"templates/appointments/update.jsp");
					} else if (action.equals("deleteAppointment")) {
						dispatcher = request.getRequestDispatcher(
								"templates/appointments/delete.jsp");
					}
					break;
				}
			}
		} catch (SQLException ex) {
			LOGGER.error("Error inserting appointment", ex);
		} finally {
			if (appointmentDao != null) {
				try {
					appointmentDao.closeConnection();
				} catch (SQLException ex) {
					LOGGER.error("Error closing connection of appointment", ex);
				}
			}

			if (animalDao != null) {
				try {
					animalDao.closeConnection();
				} catch (SQLException ex) {
					LOGGER.warn("Error closing connection of animal", ex);
				}
			}
		}

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "AppointmentServlet";
	}// </editor-fold>
}
