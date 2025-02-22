package com.jspiders.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.jspiders.springmvc.dto.Contact;
import com.jspiders.springmvc.dto.User;

@Component
public class ConatactDAO {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private Query query;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addContact(Contact contactDTO) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(contactDTO);
		entityTransaction.commit();
		closeConnection();
	}

	public List<Contact> viewContact() {
		openConnection();
		query = entityManager.createQuery("SELECT contacts FROM Contact contacts ");
		List<Contact> contacts = query.getResultList();
		closeConnection();
		return contacts;

	}

	private void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("contact_manager");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private void closeConnection() {
		if (entityManagerFactory != null)
			entityManagerFactory.close();
		if (entityManager != null)
			entityManager.close();
		if (entityTransaction != null) {
			if (entityTransaction.isActive())
				entityTransaction.rollback();
		}
	}

	public void deleteContactById(int id) {
		openConnection();
		entityTransaction.begin();
		Contact contact = entityManager.find(Contact.class, id);
		entityManager.remove(contact);
		entityTransaction.commit();
		closeConnection();
	}

	public Contact findContact(int id) {
		openConnection();
		Contact contact = entityManager.find(Contact.class, id);
		closeConnection();
		return contact;
	}

	public void updateContact(int id, String firstName, String lastName, String email, long mobile, long work,
			String gender, String dob, String address, String website) {
		openConnection();
		entityTransaction.begin();
		Contact contact = entityManager.find(Contact.class, id);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		contact.setMobile(mobile);
		contact.setWork(work);
		contact.setGender(gender);
		contact.setDob(dob);
		contact.setAddress(address);
		contact.setWebsite(website);
		entityManager.persist(contact);
		entityTransaction.commit();
		closeConnection();
	}

	public List<Contact> findContacts(String search) {
		openConnection();
		query = entityManager.createQuery(
				"SELECT contacts FROM Contact contacts WHERE contacts.firstName like:search OR contacts.lastName like:search OR contacts.email like:search OR contacts.mobile like:search OR contacts.work like:search OR contacts.website like:search");
		query.setParameter("search", "%" + search + "%");
		List<Contact> contacts = (List<Contact>) query.getResultList();
		closeConnection();
		return contacts;

	}

	public void deleteContactByUser(User user) {
		List<Contact> contacts = viewContact();
		for (Contact contact : contacts) {
			if (user.getId() == contact.getUser().getId()) {
				openConnection();
				entityTransaction.begin();
				Contact contact2 = entityManager.find(Contact.class, contact.getId());
				entityManager.remove(contact2);
				entityTransaction.commit();
				closeConnection();
			}
		}
	}

	public List<Contact> sort(User user, String value) {
		openConnection();
		query = entityManager.createQuery("SELECT contacts FROM Contact contacts ORDER BY " + value);
		List<Contact> resultList = query.getResultList();
		closeConnection();
		return resultList;
	}

}
