package com.portal.backend.clientinterviewtracker.util;

public class EmailConstants {

	public static final String TEMP_PASSWORD_SUBJECT = "Your Temporary Password";

	public static final String TEMP_PASSWORD_TEXT = "Dear User,\n\n" + "As requested, here is your temporary password:\n\n" + "%s\n\n" + // Placeholder for the temporary password
			"Please use this temporary password to log in and reset your password within the next 48 hours.\n\n"
			+ "If you did not request this, please contact our support team immediately.\n\n" + "Best regards,\n" + "The Team";
}
