package br.edu.utfpr.tds.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenha {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("12345")); //Senha que deseja criptografar
		//$2a$10$BvoTkvYkfnN4FsdEoT1Q..vOBABo.nyBPWH9/W/XQh7rprp6Gjchy
	}
}
