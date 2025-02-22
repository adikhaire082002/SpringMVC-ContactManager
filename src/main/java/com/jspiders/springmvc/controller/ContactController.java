package com.jspiders.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc.dto.Contact;
import com.jspiders.springmvc.dto.User;
import com.jspiders.springmvc.service.Service;

@Controller
public class ContactController {

	@Autowired
	private Service service;

	@RequestMapping("/add-contact-page")
	protected String getAddContactPage(HttpSession httpSession, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			modelMap.addAttribute("user", user);
			return "add_contatct";
		} else
			return "index";
	}

	@RequestMapping(path = "/add-contact", method = RequestMethod.POST)
	protected String addConatact(@RequestParam(name = "first_name") String firstName,
			@RequestParam(name = "last_name") String lastName, @RequestParam(name = "email") String email,
			@RequestParam(name = "mobile") long mobile, @RequestParam(name = "work") long work,
			@RequestParam(name = "gender") String gender, @RequestParam(name = "dob") String dob,
			@RequestParam(name = "address") String address, @RequestParam(name = "website") String website,
			HttpSession httpSession, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			boolean contactAdded = service.addConatact(firstName, lastName, email, mobile, work, gender, dob, address,
					website, user);
			if (contactAdded) {
				modelMap.addAttribute("message", "Contact added");
				modelMap.addAttribute("user", user);
				viewContact(httpSession, modelMap);
				return "contacts";
			}

			else {
				modelMap.addAttribute("message", "Something went wrong");
				modelMap.addAttribute("user", user);
				return "contacts";
			}
		} else
			return "index";

	}

	@RequestMapping(path = "/contacts")
	protected String viewContact(HttpSession httpSession, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			List<Contact> contacts = service.view(user);
			if (contacts != null) {
				modelMap.addAttribute("user", user);
				modelMap.addAttribute("contacts", contacts);
				return "contacts";
			} else {
				modelMap.addAttribute("message", "Contacts not found");
				modelMap.addAttribute("user", user);
				return "contacts";
			}
		} else
			return "index";

	}

	@RequestMapping(path = "/deleteContactById")
	protected String deleteContactById(@RequestParam(name = "id") int id, HttpSession httpSession, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			Contact contact = service.findContact(id);
			boolean deletedContact = service.deleteContactById(id);

			if (deletedContact) {
				modelMap.addAttribute("message", "Contact Deleted Successfully");
				List<Contact> contacts = service.view(user);
				if (contacts != null) {
					modelMap.addAttribute("contacts", contacts);
				}
				modelMap.addAttribute("user", user);
				return "contacts";
			} else {
				modelMap.addAttribute("message", "Something Went Wrong");
				modelMap.addAttribute("user", user);
				viewContact(httpSession, modelMap);

				return "contacts";
			}
		} else
			return "index";

	}

	@RequestMapping(path = "/update-contact-page")
	protected String updateContact(@RequestParam(name = "id") int id, HttpSession httpSession, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			Contact contact = service.findContact(id);
			if (contact != null) {
				modelMap.addAttribute("contact", contact);
				modelMap.addAttribute("user", user);
				return "update";
			} else {
				modelMap.addAttribute("message", "Contact not found");
				return "contacts";
			}
		} else
			return "index";

	}

	@RequestMapping(path = "/updateContact")
	protected String updateContact(@RequestParam(name = "first_name") String firstName,
			@RequestParam(name = "last_name") String lastName, @RequestParam(name = "email") String email,
			@RequestParam(name = "mobile") long mobile, @RequestParam(name = "work") long work,
			@RequestParam(name = "gender") String gender, @RequestParam(name = "dob") String dob,
			@RequestParam(name = "address") String address, @RequestParam(name = "website") String website,
			@RequestParam(name = "id") int id,HttpSession httpSession, ModelMap modelMap) {
		User user = (User)httpSession.getAttribute("user");
		if(user!=null) {
		Contact contact = service.findContact(id);
		boolean update = service.updateContact(id, firstName, lastName, email, mobile, work, gender, dob, address,
				website);
		if (update) {
			modelMap.addAttribute("message", "Contact Updated Successfully");
			List<Contact> contacts = service.view(user);
			if (contacts != null) {
				modelMap.addAttribute("contacts", contacts);
			}
			modelMap.addAttribute("user", user);
			return "contacts";

		} else {
			modelMap.addAttribute("message", "Something Went Wrong");
			modelMap.addAttribute("user", user);
			viewContact(httpSession, modelMap);

			return "contacts";
		}
		}
		else
			return "index";

	}

	@RequestMapping(path = "/search-contact")
	protected String search(HttpSession httpSession, @RequestParam(name = "search") String search,
			ModelMap modelMap) {
		
		User user = (User)httpSession.getAttribute("user");
		if(user!=null) {
		List<Contact> contacts = service.findContact(search, user.getId());
		if (contacts != null) {
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("contacts", contacts);
			return "contacts";
		} else {
			modelMap.addAttribute("message", "Contacts not found");
			modelMap.addAttribute("user", user);
			return "contacts";
		}

	}
		else
			return "index";
	}
	
	@RequestMapping(path = "/sort")
	protected String sortContact(HttpSession httpSession , @RequestParam(name="sortOption")String value,ModelMap modelMap) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			List<Contact> contacts = service.sort(user,value);
			if (contacts != null) {
				modelMap.addAttribute("contacts", contacts);
				return "contacts";
			} else {
				modelMap.addAttribute("message", "Contacts not found");
				modelMap.addAttribute("user", user);
				return "contacts";
			}
		} else
			return "index";
		
	}

}
