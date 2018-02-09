package tech.overpass.rpnajava1.util;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogConfigurator {
	
	public static void configure(Logger logger) throws Exception {
		FileHandler fileHandler;
		fileHandler = new FileHandler("log.txt", true);
		SimpleFormatter formatter = new SimpleFormatter();
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);
	}
	
}
