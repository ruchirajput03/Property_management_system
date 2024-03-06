package com.anudip.functionality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.anudip.daoclasses.OwnerDAO;
import com.anudip.daoclasses.PaymentDAO;
import com.anudip.daoclasses.PropertyDAO;
import com.anudip.daoclasses.TenantDAO;
import com.anudip.entityclasses.HibernateUtil;
import com.anudip.entityclasses.Owner;
import com.anudip.entityclasses.Payment;
import com.anudip.entityclasses.Property;
import com.anudip.entityclasses.Tenant;

public class AdminFunc {
	private static OwnerDAO ownerDao = new OwnerDAO();
	private static TenantDAO tenantDao = new TenantDAO();
	private static PropertyDAO propertyDao = new PropertyDAO();
	private static PaymentDAO paymentDao = new PaymentDAO();

	public void adminFunctionalities() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nAdmin Functionalities:");
			System.out.println("1. Add Owner");
			System.out.println("2. Remove Owner");
			System.out.println("3. View Owners");
			System.out.println("4. Add Tenant");
			System.out.println("5. Remove Tenant");
			System.out.println("6. View Tenants");
			System.out.println("7. Add Property");
			System.out.println("8. Remove Property");
			System.out.println("9. View Properties");
			System.out.println("10. View Payments");
			System.out.println("11. Exit");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				// Add owner
				Owner newOwner = new Owner();
				System.out.print("Enter first name: ");
				newOwner.setFirstName(scanner.next());
				System.out.print("Enter last name: ");
				newOwner.setLastName(scanner.next());
				System.out.print("Enter email: ");
				newOwner.setEmail(scanner.next());
				System.out.print("Enter phone: ");
				newOwner.setPhone(scanner.next());
				System.out.print("Enter address: ");
				newOwner.setAddress(scanner.next());

				ownerDao.addOwner(newOwner);
				System.out.println("Owner added successfully!");
				break;
			case 2:
				// delete the owner
				System.out.print("Enter owner ID to delete: ");
				int deleteOwnerId = scanner.nextInt();
				ownerDao.deleteOwner(deleteOwnerId);
				System.out.println("Owner deleted successfully!");
				break;
			case 3:
				// List all Owners
				List<Owner> ownerList = ownerDao.getAllUsers();
				for (Owner owner : ownerList) {
					System.out.println(owner);
				}
				break;
			case 4:
				// Adding Tenant
				Tenant newTenant = new Tenant();
				System.out.print("Enter first name: ");
				newTenant.setFirstName(scanner.next());
				System.out.print("Enter last name: ");
				newTenant.setLastName(scanner.next());
				System.out.print("Enter email: ");
				newTenant.setEmail(scanner.next());
				System.out.print("Enter phone: ");
				newTenant.setPhone(scanner.next());

				// Handling Date input
				try {
					System.out.print("Enter lease start date (yyyy-MM-dd): ");
					String leaseStartDateStr = scanner.next();
					Date leaseStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaseStartDateStr);
					newTenant.setLeaseStartDate(leaseStartDate);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
					break;
				}

				try {
					System.out.print("Enter lease End date (yyyy-MM-dd): ");
					String leaseEndDateStr = scanner.next();
					Date leaseEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaseEndDateStr);
					newTenant.setLeaseEndDate(leaseEndDate);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
					break;
				}
				System.out.print("Enter emergency contact name: ");
				newTenant.setEmergencyContactName(scanner.next());
				newTenant.setEmergencyContactName(scanner.nextLine());
				System.out.print("Enter Property ID: ");
				long propertyId = scanner.nextLong();
				Property property = getPropertyById(propertyId);
				if (property != null) {
					newTenant.setProperty(property);
				} else {
					System.out.println("Property not found with ID " + propertyId + ". Tenant creation aborted.");
					return;
				}

				tenantDao.addTenant(newTenant);
				System.out.println("Tenant added successfully!");
				break;
			case 5:
//				Removing Tenant
				System.out.print("Enter tenant ID to delete: ");
				int deleteTenantId = scanner.nextInt();
				tenantDao.deleteTenant(deleteTenantId);
				System.out.println("Tenant deleted successfully!");
				break;
			case 6:
//				list all tenant
				List<Tenant> tenantList = tenantDao.getAllTenant();
				for (Tenant tenant : tenantList) {
					System.out.println(tenant);
				}
				break;
			case 7:
				// Add Property
				Property newProperty = new Property();
				System.out.print("Enter Owner ID (existing owner): ");
				int ownerID = scanner.nextInt();

				Owner owner = new Owner();
				owner.setOwnerId(ownerID);
				newProperty.setOwner(owner);

				System.out.print("Enter property address: ");
				newProperty.setAddress(scanner.next());
				newProperty.setAddress(scanner.nextLine());
				System.out.print("Enter property description: ");
				newProperty.setDescription(scanner.next());
				newProperty.setDescription(scanner.nextLine());
				System.out.print("Enter rental fees: ");
				newProperty.setRentalFees(scanner.nextDouble());
				System.out.print("Enter availability status: ");
				newProperty.setAvailabilityStatus(scanner.next());
				newProperty.setAvailabilityStatus(scanner.nextLine());

				propertyDao.addProperty(newProperty);
				System.out.println("Property added successfully!");
				break;
			case 8:
				// Delete Property
				System.out.print("Enter property ID to delete: ");
				long deletePropertyId = scanner.nextLong();
				propertyDao.deleteProperty(deletePropertyId);
				System.out.println("Property deleted successfully!");
				break;
			case 9:
				// View all property
				List<Property> propertyList = propertyDao.getAllProperty();
				for (Property properti : propertyList) {
					System.out.println(properti);
				}
				break;
			case 10:
				// View all Payment
				List<Payment> paymentList = paymentDao.getAllPaymentlist();
				for (Payment payment : paymentList) {
					System.out.println(payment);
				}
				break;

			case 11:
				System.out.println("Exiting Admin Functionalities.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}
		}
	}

	private static Property getPropertyById(long propertyId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Property.class, propertyId);
		}
	}

}
