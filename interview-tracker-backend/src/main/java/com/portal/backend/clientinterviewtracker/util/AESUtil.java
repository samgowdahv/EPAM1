package com.portal.backend.clientinterviewtracker.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class AESUtil {

	private static final String ALGO = "AES";
	private byte[] keyValue;

	@Value("${aes.secret.key}")
	private String secretKey;

	@PostConstruct
	private void init() {
		keyValue = secretKey.getBytes(StandardCharsets.UTF_8);
	}

	public String encrypt(String normalText) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);

		byte[] encryptedText = c.doFinal(normalText.getBytes());

		// Encoding Characters which are not allowed in the URL
		return Base64.encodeBase64URLSafeString(encryptedText);
	}

	public String decrypt(String encryptedEncodedText) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);

		// Decoding URL
		String decodedText = URLDecoder.decode(encryptedEncodedText, StandardCharsets.UTF_8.toString());
		byte[] base64DecodedText = Base64.decodeBase64(decodedText.getBytes());

		// Decrypting URL
		byte[] decodedDecryptedTxt = c.doFinal(base64DecodedText);
		return new String(decodedDecryptedTxt);
	}

	public String decryptPKCS7Padding(String encryptedEncodedText) throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		byte[] keyBytes = keyValue;
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] base64DecodedText = Base64.decodeBase64(encryptedEncodedText.getBytes());
		byte[] plainText = new byte[cipher.getOutputSize(base64DecodedText.length)];

		int ptLength = cipher.update(base64DecodedText, 0, base64DecodedText.length, plainText, 0);
		ptLength += cipher.doFinal(plainText, ptLength);

		return new String(plainText);
	}

	private Key generateKey() throws Exception {
		return new SecretKeySpec(keyValue, ALGO);
	}
}
