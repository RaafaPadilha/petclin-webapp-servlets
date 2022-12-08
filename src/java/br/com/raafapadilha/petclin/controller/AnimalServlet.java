package br.com.raafapadilha.petclin.controller;

import br.com.raafapadilha.petclin.dao.AnimalDao;
import br.com.raafapadilha.petclin.enumeration.AnimalType;
import br.com.raafapadilha.petclin.enumeration.Gender;
import br.com.raafapadilha.petclin.model.Animal;
import br.com.raafapadilha.petclin.model.factory.AnimalFactory;
import br.com.raafapadilha.petclin.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.inject.Inject;
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
@WebServlet(name = "AnimalServlet", urlPatterns = {"/animal"})
public class AnimalServlet extends HttpServlet {

	private final Logger LOGGER = LoggerFactory.getLogger(AnimalServlet.class);

	@Inject
	private AnimalFactory animalFactory;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnimalDao dao = null;
		RequestDispatcher dispatcher = null;
		String action = request.getParameter("action");

		try {
			dao = new AnimalDao();

			switch (action) {
				case "insert": {
					dispatcher = request.getRequestDispatcher("templates/animals/list.jsp");
					String name = request.getParameter("name");
					String breed = request.getParameter("breed");
					String color = request.getParameter("color");
					String gender = request.getParameter("gender").toUpperCase();
					LocalDate birthDate = Util.getDate(request.getParameter("birthDate"));
					Float weigth = Float.valueOf(request.getParameter("weigth"));
					String type = request.getParameter("animalType").toUpperCase();

					Animal animal = animalFactory.getAnimal(AnimalType.valueOf(type));
					animal.setName(name);
					animal.setBreed(breed);
					animal.setColor(color);
					animal.setGender(Gender.valueOf(gender));
					animal.setBirthDate(birthDate);
					animal.setWeigth(weigth);

					dao.create(animal);
					break;
				}

				case "update": {
					Long id = Long.valueOf(request.getParameter("id"));
					String name = request.getParameter("name");
					String breed = request.getParameter("breed");
					String color = request.getParameter("color");
					String gender = request.getParameter("gender").toUpperCase();
					LocalDate birthDate = Util.getDate(request.getParameter("birthDate"));
					Float weigth = Float.valueOf(request.getParameter("weigth"));
					String type = request.getParameter("animalType").toUpperCase();

					Animal animal = animalFactory.getAnimal(AnimalType.valueOf(type));
					animal.setId(id);
					animal.setName(name);
					animal.setBreed(breed);
					animal.setColor(color);
					animal.setGender(Gender.valueOf(gender));
					animal.setBirthDate(birthDate);
					animal.setWeigth(weigth);

					dao.update(animal);
					dispatcher = request.getRequestDispatcher("templates/animals/list.jsp");
					break;
				}

				case "delete": {
					Long id = Long.valueOf(request.getParameter("id"));
					String type = request.getParameter("animalType").toUpperCase();

					Animal animal = animalFactory.getAnimal(AnimalType.valueOf(type));
					animal.setId(id);

					dao.delete(animal);
					dispatcher = request.getRequestDispatcher("templates/animals/list.jsp");
				}

				default: {
					Long id = Long.valueOf(request.getParameter("id"));
					Animal animal = dao.findById(id);
					request.setAttribute("animal", animal);

					if (action.equals("updateAnimal")) {
						dispatcher = request.getRequestDispatcher(
								"templates/animals/update.jsp");
					} else if (action.equals("deleteAnimal")) {
						dispatcher = request.getRequestDispatcher(
								"templates/animals/delete.jsp");
					}
					break;
				}
			}
		} catch (SQLException ex) {
			LOGGER.error("Error inserting animal", ex);
		} finally {
			if (dao != null) {
				try {
					dao.closeConnection();
				} catch (SQLException ex) {
					LOGGER.warn("Error closing connection", ex);
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
		return "AnimalServlet";
	}// </editor-fold>
}
