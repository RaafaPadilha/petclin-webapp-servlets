package br.com.raafapadilha.petclin.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RaafaPadilha
 */
public class LoggerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		try {
			System.out.println(10 / 0);
		} catch (ArithmeticException ex) {
			LOGGER.error("Cannot divid by 0", ex);
		}
	}
}
