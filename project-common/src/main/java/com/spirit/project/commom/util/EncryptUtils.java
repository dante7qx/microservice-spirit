package com.spirit.project.commom.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtils {
	private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

	public static String encrypt(String rawPassword) {
		return bcryptEncoder.encode(rawPassword);
	}
	
	public static boolean match(String rawPassword, String password) {
		return bcryptEncoder.matches(rawPassword, password);
	}
	
	public static void main(String[] args) {
		// for maven package
	}
}
