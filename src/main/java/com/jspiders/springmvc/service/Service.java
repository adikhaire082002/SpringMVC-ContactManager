package com.jspiders.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspiders.springmvc.dao.ConatactDAO;
import com.jspiders.springmvc.dao.UserDAO;
import com.jspiders.springmvc.dto.Contact;
import com.jspiders.springmvc.dto.User;

@Component
public class Service {

	@Autowired
	private ConatactDAO contactDAO;

	@Autowired
	private UserDAO userDAO;

	public boolean addConatact(String firstName, String lastName, String email, long mobile, long work, String gender,
			String dob, String address, String website, User user) {
		Contact contact = new Contact();
		contact.setLastName(lastName);
		contact.setFirstName(firstName);
		contact.setEmail(email);
		contact.setMobile(mobile);
		contact.setWork(work);
		contact.setGender(gender);
		contact.setDob(dob);
		contact.setAddress(address);
		contact.setWebsite(website);
		contact.setUser(user);

		try {
			contactDAO.addContact(contact);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Contact> view(User user) {
		List<Contact> contacts = contactDAO.viewContact();
		List<Contact> contacts1 = new ArrayList<Contact>();
		if (contacts.size() > 0) {
			for (Contact contact : contacts) {
				if (contact.getUser().getId() == user.getId()) {
					contacts1.add(contact);
				}
			}
			if (contacts.size() > 0)
				return contacts1;
			else
				return null;
		}

		else
			return null;
	}

	public boolean deleteContactById(int id) {

		try {
			contactDAO.deleteContactById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Contact findContact(int id) {
		try {
			Contact contact = contactDAO.findContact(id);
			return contact;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean updateContact(int id, String firstName, String lastName, String email, long mobile, long work,
			String gender, String dob, String address, String website) {
		try {
			contactDAO.updateContact(id, firstName, lastName, email, mobile, work, gender, dob, address, website);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean addUser(String name, String surname, String username, String password, long mobile, String email,
			String securtiyQue, String anwser) {
		System.out.println("service");
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setSecurtiyQue(securtiyQue);
		user.setAnswer(anwser);
		try {
			userDAO.addContact(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean findUser(String username) {
		try {
			User user = userDAO.findUsername(username);
			if (user != null)
				if (username.equals(user.getUsername()))
					return true;
				else
					return false;
			else
				return false;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean login(String username, String password) {
		try {
			User login = userDAO.login(username, password);
			try {
				User user = userDAO.findUsername(username);
				if (user != null) {
					if (username.equals(login.getUsername()) && password.equals(login.getPassword()))
						return true;
					else
						return false;
				} else
					return false;
			} catch (Exception e) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}

	public User user(String username) {
		User user = userDAO.findUsername(username);
		return user;

	}

	public boolean findMobile(long mobile) {

		try {
			User user = userDAO.findMobile(mobile);
			if (user != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean findEmail(String email) {
		try {
			User user = userDAO.findEmail(email);
			if (user != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}

	}

	public User findUser(int id) {
		User user = userDAO.find(id);
		return user;

	}

	public boolean updateUser(int id, String name, String surname, String username, String password, long mobile,
			String email, String securtiyQue, String answer) {
		try {
			userDAO.updateUser(id, name, surname, username, password, mobile, email, securtiyQue, answer);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean findUser(String username, User user) {

		try {
			User user2 = userDAO.findUsers(username, user);
			if (user2 != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean findMobile(long mobile, User user) {
		try {
			User user2 = userDAO.findMobile(mobile, user);
			if (user2 != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean findEmail(String email, User user) {
		try {
			User user2 = userDAO.findEmail(email, user);
			if (user2 != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Contact> findContact(String search, int id) {
		List<Contact> contacts = contactDAO.findContacts(search);
		List<Contact> contacts1 = new ArrayList<Contact>();
		if (contacts.size() > 0) {
			for (Contact contact : contacts) {
				if (contact.getUser().getId() == id) {
					contacts1.add(contact);
				}
			}
			if (contacts.size() > 0)
				return contacts1;
			else
				return null;
		}

		else
			return null;

	}

	public void deleteContactByUser(User user) {
		try {
			contactDAO.deleteContactByUser(user);
			userDAO.deleteUser(user);
		} catch (Exception e) {
		}
		
	}

	public List<Contact> sort(User user, String value) {
		try {
			List<Contact> sort = contactDAO.sort(user,value);
			if(sort.size()>0) {
				List<Contact> contacts = new ArrayList();
				for (Contact contact : sort) {
					if(user.getId() == contact.getUser().getId()) {
						contacts.add(contact);
					}
				}
				return contacts;
			}
			else {
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}
		
	}
}
