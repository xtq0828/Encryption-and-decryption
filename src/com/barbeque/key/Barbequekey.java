package com.barbeque.key;

import java.io.File;
import java.io.IOException;

public class Barbequekey {

	String cipher_command = "";
	String private_key_command = "";
	String decipher_command = "";
	String ciphername = "";
	String show_command = "";

	// InputStreamReader ir=new InputStreamReader(process.getInputStream());

	// BufferedReader input = new BufferedReader (ir);

	// String line;

	// while ((line = input.readLine ()) != null){

	// System.out.println(line);

	// }
	public void cd() throws IOException {
		// Runtime.getRuntime().exec ("cd /home/barbeque/cpabe-0.11/");
	}

	public void PM_key() throws IOException {
		Runtime.getRuntime().exec("cpabe-setup");
	}

	public void cipher(String P_key, String filename, String require)
			throws IOException {
		cipher_command = "cpabe-enc" + " " + P_key + " " + filename + " "
				+ require;
		Runtime.getRuntime().exec(cipher_command);
		System.out.println(cipher_command);
	}

	public void private_key(String private_key, String P_key, String M_key,
			String require1, String require2) throws IOException {
		if (require2.equals("")) {
			private_key_command = "cpabe-keygen -o" + " " + private_key + " "
					+ P_key + " " + M_key + " " + require1;
		} else {
			private_key_command = "cpabe-keygen -o" + " " + private_key + " "
					+ P_key + " " + M_key + " " + require1 + " "
					+ "strategy_team" + " " + require2;
		}
		System.out.println(private_key_command);
		Runtime.getRuntime().exec(private_key_command);
	}

	public void decipher(String P_key, String private_key, String filename)
			throws IOException {
		decipher_command = "cpabe-dec " + P_key + " " + private_key + " "
				+ filename;
		System.out.println(decipher_command);
		Runtime.getRuntime().exec(decipher_command);
	}

	public void show(String filename) throws IOException {
		show_command = "vim" + " " + filename;
		System.out.println(show_command);
		Runtime.getRuntime().exec(show_command);
	}

}
