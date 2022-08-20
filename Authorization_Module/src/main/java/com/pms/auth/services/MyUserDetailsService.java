package com.pms.auth.services;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pms.auth.models.JwtRequest;
import com.pms.auth.repo.AuthentificationRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
@Service
public class MyUserDetailsService implements UserDetailsService, CommandLineRunner {

	@Autowired
	private AuthentificationRepository userRepo;

	/**
	 * 
	 * UserName Provider This method checks the username and provide the user when
	 * the user found in database
	 * 
	 * @param Username
	 * @return User
	 * 
	 */

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtRequest myUser;
		if (userRepo.existsById(username)) {
			myUser = userRepo.findById(username).get();
			String password = myUser.getPassword();
			log.info("User is found :" + username);
			return new User(username, password, new ArrayList<>());
			
		} else {
			
			log.debug("User not found with username" + username);
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

	/**
	 * 
	 * Override method of run This will run when the spring application starts
	 * This method will load all the users from Resourse csv file to H2 Database
	 * 
	 * bCryptPasswordEncoder used for Password Encoding path: /h2-console
	 * 
	 */

	@Override
	public void run(String... args) throws Exception {

		// File UserDetialsCsv = new File("src/main/resources/userCredentials.csv");
		InputStream inputStream = getClass().getResourceAsStream("/userCredentials.csv");
		Scanner scnObj = new Scanner(inputStream);
		scnObj.nextLine(); // for header scan

		while (scnObj.hasNextLine()) {

			String[] userdetial = scnObj.nextLine().split(",");
			String username = userdetial[0];
			String password = userdetial[1];

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
			String Encodedpassword = bCryptPasswordEncoder.encode(password);

			JwtRequest myUser = new JwtRequest(username, Encodedpassword);
			userRepo.save(myUser);
			log.info(username + ": User has been loaded into H2 Database");
		}
		scnObj.close();
	}

}
