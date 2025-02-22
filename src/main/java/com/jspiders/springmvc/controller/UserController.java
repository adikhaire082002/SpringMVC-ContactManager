package com.jspiders.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc.dto.Contact;
import com.jspiders.springmvc.dto.User;
import com.jspiders.springmvc.service.Service;

@Controller
public class UserController {
	@Autowired
	private Service service;

	@RequestMapping(path = "/home")
	protected String home() {
		return "index";
	}

	@RequestMapping(path = "/signup")
	protected String signUP(HttpSession httpSession) {
		httpSession.invalidate();
		return "signup";
	}

	@RequestMapping(path = "/userupdate")
	protected String userupdate(HttpSession httpSession, ModelMap modelMap) {
		User user = (User)httpSession.getAttribute("user");
		if(user != null) {
		modelMap.addAttribute("user", user);
		return "userUpdate";
		}
		else {
			return "index";
		}
	}

	@RequestMapping(path = "/usersignup")
	protected String userSignup(@RequestParam(name = "name") String name,
			@RequestParam(name = "surname") String surname, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "mobile") long mobile,
			@RequestParam(name = "email") String email, @RequestParam(name = "security_question") String securtiyQue,
			@RequestParam(name = "answer") String answer, ModelMap modelMap) {
		boolean user1 = service.findUser(username);
		boolean user2 = service.findMobile(mobile);
		boolean user3 = service.findEmail(email);
		if (user1 || user2 || user3) {
			if (user1) {
				modelMap.addAttribute("message", "Username already Exits");
			}
			if (user2) {
				modelMap.addAttribute("message1", "Mobile already Exits");
			}
			if (user3) {
				modelMap.addAttribute("message2", "email already Exits");
			}
			return "signup";
		}

		else {
			boolean user = service.addUser(name, surname, username, password, mobile, email, securtiyQue, answer);
			if (user) {
				return "index";
			} else {
				modelMap.addAttribute("message", "Moible number or email already exits");
				return "signup";
			}

		}

	}

	@RequestMapping(path = "/login")
	protected String login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, ModelMap modelMap, HttpSession httpSession) {
		boolean user = service.findUser(username);
		if (user) {
			boolean login = service.login(username, password);
			if (login) {
				User user1 = service.user(username);
				modelMap.addAttribute("user", user1);
				List<Contact> contacts = service.view(user1);
				modelMap.addAttribute("contacts", contacts);
				httpSession.setAttribute("user", user1);
				httpSession.setMaxInactiveInterval(60 * 60 * 24);
				return "contacts";
			} else {
				modelMap.addAttribute("message", "Incorrect Password");
				return "index";
			}
		} else {
			modelMap.addAttribute("message", "Username not found");
			return "index";
		}

	}

	@RequestMapping(path = "/updateuser")
	protected String updateUser(@RequestParam(name = "name") String name,
			@RequestParam(name = "surname") String surname, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "mobile") long mobile,
			@RequestParam(name = "email") String email, @RequestParam(name = "security_question") String securtiyQue,
			@RequestParam(name = "answer") String answer, HttpSession httpSession, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			boolean user1 = service.findUser(username, user);
			boolean user2 = service.findMobile(mobile, user);
			boolean user3 = service.findEmail(email, user);
			if (user1 || user2 || user3) {
				if (user1) {
					modelMap.addAttribute("message", "Username already Exits");
				}
				if (user2) {
					modelMap.addAttribute("message1", "Mobile already Exits");
				}
				if (user3) {
					modelMap.addAttribute("message2", "email already Exits");
				}
				modelMap.addAttribute("user", user);
				return "userUpdate";
			} else {
				boolean updateUser = service.updateUser(user.getId(), name, surname, username, password, mobile, email,
						securtiyQue, answer);
				if (updateUser) {
					modelMap.addAttribute("user", user);
					return "contacts";
				} else {
					modelMap.addAttribute("message", "Something went wrong");
					modelMap.addAttribute("user", user);
					return "userUpdate";
				}
			}
		} else {
			return "index";
		}

	}
	
	@RequestMapping(path = "/delete-user")
	protected String deleteUser(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		service.deleteContactByUser(user);
		return "index";
		
	}
}
