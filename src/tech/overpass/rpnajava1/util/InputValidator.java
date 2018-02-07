package tech.overpass.rpnajava1.util;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.InetAddressValidator;

public class InputValidator {
	
	public static boolean isUserNameValid(String userName) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]{4,20}$");
		return pattern.matcher(userName).matches();
	}
	
	public static boolean isPortNumberValid(String portString) {
		int portNumber;
		try {
			portNumber = Integer.parseInt(portString);
		} catch (NumberFormatException e) {
			return false;
		}
		return portNumber > 1024 && portNumber <= 65536;
	}
	
	public static boolean isIPAddressValid(String ipAddress) {
		InetAddressValidator ipValidator = InetAddressValidator.getInstance();
		if (ipValidator.isValid(ipAddress)) {
			return true;
		}
		return false;
	}
}
