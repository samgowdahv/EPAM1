package com.portal.backend.clientinterviewtracker.service;

public interface EmailService {
	void sendSimpleEmail(String to, String tempPassword) throws Exception;
}
