package com.portal.backend.clientinterviewtracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.portal.backend.clientinterviewtracker.util.AESUtil;
import com.portal.backend.clientinterviewtracker.util.EmailConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private AESUtil aesUtil;

	@Value("${spring.mail.username}")
	private String emailFrom;

	public void sendSimpleEmail(String to, String tempPassword) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(EmailConstants.TEMP_PASSWORD_SUBJECT);
		message.setText(String.format(EmailConstants.TEMP_PASSWORD_TEXT, aesUtil.decrypt(tempPassword)));
		message.setFrom(emailFrom);
		try {
			mailSender.send(message);
			logger.info("Email sent successfully to {}", to);
		} catch (MailException e) {
			logger.error("Failed to send email to {}. Exception: {}", to, e.getMessage(), e);
			throw new RuntimeException("Failed to send email");
		}
	}
}
