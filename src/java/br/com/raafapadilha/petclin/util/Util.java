package br.com.raafapadilha.petclin.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RaafaPadilha
 */
public class Util {

	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static LocalDate getDate(String date) {
		LocalDate d = null;

		try {
			d = LocalDate.parse(date, dtf);
		} catch (DateTimeParseException ex) {
			LOGGER.error("Failed to parse date", ex);
		}

		return d;
	}
}
