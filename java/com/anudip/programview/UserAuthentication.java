package com.anudip.programview;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import com.anudip.entityclasses.HibernateUtil;
import com.anudip.entityclasses.User;
import com.anudip.functionality.AdminFunc;
import com.anudip.functionality.OwnerFunc;
import com.anudip.functionality.TenantFunc;

public class UserAuthentication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			int choice;
			{
				System.out.println("1. Sign In");
				System.out.println("2. Sign Up");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine(); // Consume the newline

				switch (choice) {
				case 1:
					signIn();
					break;
				case 2:
					signUp();
					break;
				case 3:
					System.out.println("Exiting program.");
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}

			}

		} finally {
			scanner.close();
		}
	}

	private static void signIn() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter username or email: ");
		String usernameOrEmail = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			// Check user credentials
			User user = getUserByUsernameOrEmailAndPassword(session, usernameOrEmail, password);

			if (user != null) {
				// Authentication successful
				System.out.println("Authentication successful!");

				// Perform tasks based on user type
				if ("Admin".equalsIgnoreCase(user.getUserType())) {
					// Tasks for admin user
					System.out.println("Welcome Admin! Perform admin tasks.");
					AdminFunc adminfunc = new AdminFunc();
					adminfunc.adminFunctionalities();
				} else if ("Owner".equalsIgnoreCase(user.getUserType())) {
					// Tasks for normal user
					System.out.println("Welcome Owner! Perform owner tasks.");
					OwnerFunc ownerfunc = new OwnerFunc();
					ownerfunc.OwnerFunctions();
				} else if ("Tenant".equalsIgnoreCase(user.getUserType())) {
					System.out.println("Welcome Tenant! Perform tenant tasks.");
					TenantFunc tenantfunc = new TenantFunc();
					tenantfunc.tenantFunc();
				}
			} else {
				// Authentication failed
				System.out.println("Authentication failed. Invalid username/email or password.");
			}

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	private static void signUp() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		System.out.print("Enter first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();
		System.out.print("Enter phone: ");
		String phone = scanner.nextLine();
		System.out.print("Enter user type (Admin,Owner,Tenant): ");
		String userType = scanner.nextLine();

		User newUser = new User(username, password, firstName, lastName, email, phone, userType);
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(newUser);
			transaction.commit();
			System.out.println("User registered successfully!");
		}
		System.out.println("Please signin using your username or email and Password");
		signIn();
	}

	private static User getUserByUsernameOrEmailAndPassword(Session session, String usernameOrEmail, String password) {
		String hql = "FROM User WHERE (username = :identifier OR email = :identifier) AND password = :password";
		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("identifier", usernameOrEmail);
		query.setParameter("password", password);
		query.setMaxResults(1);
		return query.uniqueResult();
	}
}
