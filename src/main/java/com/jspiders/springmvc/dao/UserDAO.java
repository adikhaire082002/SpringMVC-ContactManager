package com.jspiders.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.jspiders.springmvc.dto.User;

@Component
public class UserDAO {
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

	public void addContact(User user) {
		System.out.println("udto");
		openConnection();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
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
	
	public User findUsername(String username) {
		User user = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.username = :username");
		query.setParameter("username", username);
		user = (User) query.getSingleResult();
		closeConnection();
		return user;
		
		
		
	}

	public User login(String username, String password) {
		User user = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.username = :username AND user.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		user = (User) query.getSingleResult();
		closeConnection();
		return user;
		
		
	}

	public User findMobile(long mobile) {
		User user = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.mobile = :mobile");
		query.setParameter("mobile", mobile);
		user = (User) query.getSingleResult();
		closeConnection();
		return user;
		
	}

	public User findEmail(String email) {
		User user = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.email = :email");
		query.setParameter("email", email);
		user = (User) query.getSingleResult();
		closeConnection();
		return user;
	}
	
	public User find(int id) {
		openConnection();
		User user = entityManager.find(User.class, id);
		closeConnection();
		return user;
		
	}

	public User updateUser(int id, String name, String surname, String username, String password, long mobile,
			String email, String securtiyQue, String answer) {
		openConnection();
		User user = entityManager.find(User.class, id);
		user.setName(name);
		user.setSurname(surname);
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setSecurtiyQue(securtiyQue);
		user.setAnswer(answer);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	

	public User findUsers(String username, User user2) {
		User user = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.username = :username");
		query.setParameter("username", username);
		user = (User) query.getSingleResult();
		closeConnection();
		if(user.getId()==user2.getId()) {
			user =null;
			return user;
		}
		else {
			return user;
		}
		
		
	}

	public User findMobile(long mobile, User user) {
		User user2 = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.mobile = :mobile");
		query.setParameter("mobile", mobile);
		user2 = (User) query.getSingleResult();
		closeConnection();
		if(user.getId()==user2.getId()) {
			user =null;
			return user;
		}
		else {
			return user;
		}
	}

	public User findEmail(String email, User user) {
		User user2 = null;
		openConnection();
		query = entityManager.createQuery("Select user from User user WHERE user.email = :email");
		query.setParameter("email", email);
		user2 = (User) query.getSingleResult();
		closeConnection();
		if(user.getId()==user2.getId()) {
			user =null;
			return user;
		}
		else {
			return user;
		}
		
	}

	public void deleteUser(User user) {
		openConnection();
		entityTransaction.begin();
		User user2 = entityManager.find(User.class, user.getId());
		entityManager.remove(user2);
		entityTransaction.commit();
		closeConnection();
		
	}

}
