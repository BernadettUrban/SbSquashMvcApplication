package pti.sb_squash_MVC.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_MVC.db.Database;
import pti.sb_squash_MVC.model.Location;
import pti.sb_squash_MVC.model.Match;
import pti.sb_squash_MVC.model.Role;
import pti.sb_squash_MVC.model.User;

@Controller
public class AppController {

	@GetMapping("/")
	public String index() {

		return "index";
	}

	@GetMapping("/admin")
	public String admin() {

		return "admin";
	}

	@GetMapping("/userform")
	public String userForm() {

		return "userform.html";
	}

	public String generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		String pwd = new String(password);
		return pwd;
	}

	@GetMapping("/reguser")
	public String registration(Model model, @RequestParam(name = "name") String name) {

		User user = new User();
		user.setName(name);
		user.setPassword(generatePassword(6));
		user.setRole(Role.USER);
		user.setPasswordchanged(false);

		Database db = new Database();
		db.saveUser(user);
		db.close();

		model.addAttribute("user", user);
		return "admin";
	}

	@GetMapping("/reglocation")
	public String regLocation(Model model, @RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address) {

		Location location = new Location();
		location.setName(name);
		location.setAddress(address);
		location.setRate(1);

		// http://api.napiarfolyam.hu/?bank=mnb&valuta=eur

		/**
		 * SAXBuilder sb = new SAXBuilder(); String XML = "..."; Document document =
		 * sb.build( new StringReader(XML) );
		 * 
		 */

		Database db = new Database();
		db.saveLocation(location);
		db.close();

		model.addAttribute("location", location);
		return "admin.html";
	}

	@GetMapping("/regmatch")
	public String regMatch(Model model, @RequestParam(name = "playerone") String playerone,
			@RequestParam(name = "pointsplayerone") int pointsplayerone,
			@RequestParam(name = "playertwo") String playertwo,
			@RequestParam(name = "pointsplayertwo") int pointsplayertwo,
			@RequestParam(name = "location") String location, @RequestParam(name = "date") String date) {

		model.addAttribute("playerone", playerone);
		model.addAttribute("pointsplayerone", pointsplayerone);
		model.addAttribute("playertwo", playertwo);
		model.addAttribute("location", location);
		model.addAttribute("date", date);

		return "admin.html";
	}

	@GetMapping("/showmatches")
	public String showMatches(Model model) {
		Database db = new Database();
		List<Match> matches = db.getMatches();
		db.close();

		model.addAttribute("matches", matches);
		return "showmatches";
	}

	@GetMapping("/login")
	public String login() {

		

		return "index";
	}

	@PostMapping("/login")
	public String hello(Model model, @RequestParam(name = "name") String name,
			@RequestParam(name = "password") String pwd) {
		String targetPage = "";
		Database db = new Database();
		boolean registeredUser = db.registeredUser(name, pwd);
		String id = "";
		if (registeredUser == true) {
			
			targetPage = "showmatches";

		} else {
			targetPage = "login";
			model.addAttribute("error", "Not a registered user.");
		}

		return targetPage;
	}

}
