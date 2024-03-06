package com.anudip.programview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.anudip.daoclasses.BookingRequestDAO;
import com.anudip.daoclasses.LeaseDAO;
import com.anudip.daoclasses.OwnerDAO;
import com.anudip.daoclasses.PaymentDAO;
import com.anudip.daoclasses.PropertyDAO;
import com.anudip.daoclasses.TenantDAO;
import com.anudip.daoclasses.UserDAO;
import com.anudip.entityclasses.BookingRequest;
import com.anudip.entityclasses.HibernateUtil;
import com.anudip.entityclasses.Lease;
import com.anudip.entityclasses.Owner;
import com.anudip.entityclasses.Payment;
import com.anudip.entityclasses.Property;
import com.anudip.entityclasses.Tenant;
import com.anudip.entityclasses.User;

public class MainProgram {
	private static UserDAO userDao = new UserDAO();
	private static OwnerDAO ownerDao = new OwnerDAO();
	private static TenantDAO tenantDao = new TenantDAO();
	private static PropertyDAO propertyDao = new PropertyDAO();
	private static LeaseDAO leaseDao = new LeaseDAO();
	private static BookingRequestDAO bookingRequestDao = new BookingRequestDAO();
	private static PaymentDAO paymentDao = new PaymentDAO();

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Select Table:");
			System.out.println("1. User");
			System.out.println("2. Owner");
			System.out.println("3. Tenant");
			System.out.println("4. Property");
			System.out.println("5. Lease");
			System.out.println("6. Booking Request");
			System.out.println("7. Payment");
			System.out.println("8. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				performCrudOperation("User");
				break;

			case 2:
				performCrudOperation("Owner");
				break;

			case 3:
				performCrudOperation("Tenant");
				break;

			case 4:
				performCrudOperation("Property");
				break;

			case 5:
				performCrudOperation("Lease");
				break;

			case 6:
				performCrudOperation("BookingRequest");
				break;

			case 7:
				performCrudOperation("Payment");
				break;

			case 8:
				System.out.println("Exiting the program. Goodbye!");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 8.");
			}
		}
	}

	private static void performCrudOperation(String tableName) {

		switch (tableName) {
		case "User":
			performUserCrudOperations();
			break;

		case "Owner":
			performOwnerCrudOperations();
			break;

		case "Tenant":
			performTenantCrudOperations();
			break;

		case "Property":
			performPropertyCrudOperations();
			break;

		case "Lease":
			performLeaseCrudOperations();
			break;

		case "BookingRequest":
			performBookingRequestCrudOperations();
			break;

		case "Payment":
			performPaymentCrudOperations();
			break;
		}
	}

	private static void performUserCrudOperations() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add user)");
			System.out.println("2. Read (Get user by ID)");
			System.out.println("3. Update (Update user details)");
			System.out.println("4. Delete (Delete user)");
			System.out.println("5. List All Users");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add user)
				User newUser = new User();
				System.out.print("Enter username: ");
				newUser.setUsername(scanner.next());
				System.out.print("Enter password: ");
				newUser.setPassword(scanner.next());
				System.out.print("Enter first name: ");
				newUser.setFirstName(scanner.next());
				System.out.print("Enter last name: ");
				newUser.setLastName(scanner.next());
				System.out.print("Enter email: ");
				newUser.setEmail(scanner.next());
				System.out.print("Enter phone: ");
				newUser.setPhone(scanner.next());
				System.out.print("Enter user type: ");
				newUser.setUserType(scanner.next());

				userDao.addUser(newUser);
				System.out.println("User added successfully!");
				break;

			case 2:
				// Read (Get user by ID)
				System.out.print("Enter user ID to retrieve: ");
				int userId = scanner.nextInt();
				User retrievedUser = userDao.getUserById(userId);
				System.out.println("Retrieved User: " + retrievedUser);
				break;

			case 3:
				// Update (Update user details)
				System.out.print("Enter user ID to update: ");
				int updateUserId = scanner.nextInt();
				User updateUser = userDao.getUserById(updateUserId);

				if (updateUser != null) {
					System.out.print("Enter new email: ");
					updateUser.setEmail(scanner.next());
					userDao.updateUser(updateUser);
					System.out.println("User updated successfully!");
				} else {
					System.out.println("User not found!");
				}
				break;

			case 4:
				// Delete (Delete user)
				System.out.print("Enter user ID to delete: ");
				int deleteUserId = scanner.nextInt();
				userDao.deleteUser(deleteUserId);
				System.out.println("User deleted successfully!");
				break;

			case 5:
				// List All Users
				List<User> userList = userDao.getAllUsers();
				for (User user : userList) {
					System.out.println(user);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 6.");
			}
		}
	}

	private static void performOwnerCrudOperations() {
		// Implement CRUD operations for Owner
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add owner)");
			System.out.println("2. Read (Get owner by ID)");
			System.out.println("3. Update (Update owner details)");
			System.out.println("4. Delete (Delete owner)");
			System.out.println("5. List All Owner");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add owner)
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
				// Read (Get owner by ID)
				System.out.print("Enter owner ID to retrieve: ");
				int ownerId = scanner.nextInt();
				Owner retrievedOwner = ownerDao.getOwnerById(ownerId);
				System.out.println("Retrieved Owner: " + retrievedOwner);
				break;

			case 3:
				// Update (Update owner details)
				System.out.print("Enter owner ID to update: ");
				int updateOwnerId = scanner.nextInt();
				Owner updateOwner = ownerDao.getOwnerById(updateOwnerId);

				if (updateOwner != null) {
					System.out.print("Enter new email: ");
					updateOwner.setEmail(scanner.next());
					ownerDao.updateOwner(updateOwner);
					System.out.println("Owner updated successfully!");
				} else {
					System.out.println("Owner not found!");
				}
				break;

			case 4:
				// Delete (Delete owner)
				System.out.print("Enter owner ID to delete: ");
				int deleteOwnerId = scanner.nextInt();
				ownerDao.deleteOwner(deleteOwnerId);
				System.out.println("Owner deleted successfully!");
				break;

			case 5:
				// List All Users
				List<Owner> ownerList = ownerDao.getAllUsers();
				for (Owner owner : ownerList) {
					System.out.println(owner);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 5.");
			}
		}
	}

	private static void performTenantCrudOperations() {
		// Implement CRUD operations for Tenant
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add tenant)");
			System.out.println("2. Read (Get tenant by ID)");
			System.out.println("3. Update (Update tenant details)");
			System.out.println("4. Delete (Delete tenant)");
			System.out.println("5. List All Tenant");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add tenant)
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

				// For simplicity, let's assume you have a Property object available
				// You can fetch the Property from the database based on user input
				// and set it to the newTenant
				System.out.print("Enter Property ID: ");
				long propertyId = scanner.nextLong();
				Property property = getPropertyById(propertyId); // You need to implement this method
				if (property != null) {
					newTenant.setProperty(property);
				} else {
					System.out.println("Property not found with ID " + propertyId + ". Tenant creation aborted.");
					return;
				}

				tenantDao.addTenant(newTenant);
				System.out.println("Tenant added successfully!");
				break;
			case 2:
				// Read (Get tenant by ID)
				System.out.print("Enter tenant ID to retrieve: ");
				int tenantId = scanner.nextInt();
				Tenant retrievedTenant = tenantDao.getTenantById(tenantId);
				System.out.println("Retrieved Tenant: " + retrievedTenant);
				break;

			case 3:
				// Update (Update tenant details)
				System.out.print("Enter tenant ID to update: ");
				int updateTenantId = scanner.nextInt();
				Tenant updateTenant = tenantDao.getTenantById(updateTenantId);

				if (updateTenant != null) {
					System.out.print("Enter new email: ");
					updateTenant.setEmail(scanner.next());
					tenantDao.updateTenant(updateTenant);
					System.out.println("Tenant updated successfully!");
				} else {
					System.out.println("Tenant not found!");
				}
				break;

			case 4:
				// Delete (Delete tenant)
				System.out.print("Enter tenant ID to delete: ");
				int deleteTenantId = scanner.nextInt();
				tenantDao.deleteTenant(deleteTenantId);
				System.out.println("Tenant deleted successfully!");
				break;
			case 5:
				// List All Users
				List<Tenant> tenantList = tenantDao.getAllTenant();
				for (Tenant tenant : tenantList) {
					System.out.println(tenant);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 5.");
			}

		}
	}

	private static Property getPropertyById(long propertyId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Property.class, propertyId);
		}
	}

	private static void performPropertyCrudOperations() {
		// Implement CRUD operations for Property
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add property)");
			System.out.println("2. Read (Get property by ID)");
			System.out.println("3. Update (Update property details)");
			System.out.println("4. Delete (Delete property)");
			System.out.println("5. List All Property");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add property)
				Property newProperty = new Property();
				System.out.print("Enter Owner ID (existing owner): ");
				int ownerID = scanner.nextInt();

				// For simplicity, let's assume you have an Owner object available
				// You can fetch the Owner from the database based on user input
				// and set it to the newProperty
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

			case 2:
				// Read (Get property by ID)
				System.out.print("Enter property ID to retrieve: ");
				long propertyId = scanner.nextLong();
				Property retrievedProperty = propertyDao.getPropertyById(propertyId);
				System.out.println("Retrieved Property: " + retrievedProperty);
				break;

			case 3:
				// Update (Update property details)
				System.out.print("Enter property ID to update: ");
				long updatePropertyId = scanner.nextLong();
				Property updateProperty = propertyDao.getPropertyById(updatePropertyId);

				if (updateProperty != null) {
					System.out.print("Enter new rental fees: ");
					updateProperty.setRentalFees(scanner.nextDouble());
					propertyDao.updateProperty(updateProperty);
					System.out.println("Property updated successfully!");
				} else {
					System.out.println("Property not found!");
				}
				break;

			case 4:
				// Delete (Delete property)
				System.out.print("Enter property ID to delete: ");
				long deletePropertyId = scanner.nextLong();
				propertyDao.deleteProperty(deletePropertyId);
				System.out.println("Property deleted successfully!");
				break;

			case 5:
				// List All Users
				List<Property> propertyList = propertyDao.getAllProperty();
				for (Property property : propertyList) {
					System.out.println(property);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 6.");
			}
		}
	}

	private static void performLeaseCrudOperations() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add lease)");
			System.out.println("2. Read (Get lease by ID)");
			System.out.println("3. Update (Update lease details)");
			System.out.println("4. Delete (Delete lease)");
			System.out.println("5. List All LeaseDoc");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add lease)
				Lease newLease = new Lease();
				System.out.print("Enter Tenant ID (existing tenant): ");
				int tenantID = scanner.nextInt();

				// For simplicity, let's assume you have a Tenant object available
				// You can fetch the Tenant from the database based on user input
				// and set it to the newLease
				Tenant tenant = new Tenant();
				tenant.setTenantId(tenantID);
				newLease.setTenant(tenant);

				System.out.print("Enter Property ID (existing property): ");
				long propertyID = scanner.nextLong();

				// For simplicity, let's assume you have a Property object available
				// You can fetch the Property from the database based on user input
				// and set it to the newLease
				Property property = new Property();
				property.setPropertyId(propertyID);
				newLease.setProperty(property);

				// Handling Date input
				try {
					System.out.print("Enter lease start date (yyyy-MM-dd): ");
					String leaseStartDateStr = scanner.next();
					Date leaseStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaseStartDateStr);
					newLease.setLeaseStartDate(leaseStartDate);

					System.out.print("Enter lease end date (yyyy-MM-dd): ");
					String leaseEndDateStr = scanner.next();
					Date leaseEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaseEndDateStr);
					newLease.setLeaseEndDate(leaseEndDate);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
					break;
				}

				System.out.print("Enter monthly rent: ");
				newLease.setMonthlyRent(scanner.nextDouble());
				System.out.print("Enter lease term (in months): ");
				newLease.setLeaseTerm(scanner.nextInt());
				System.out.print("Enter security deposit: ");
				newLease.setSecurityDeposit(scanner.nextDouble());

				leaseDao.addLease(newLease);
				System.out.println("Lease added successfully!");
				break;

			case 2:
				// Read (Get lease by ID)
				System.out.print("Enter lease ID to retrieve: ");
				long leaseId = scanner.nextLong();
				Lease retrievedLease = leaseDao.getLeaseById(leaseId);
				System.out.println("Retrieved Lease: " + retrievedLease);
				break;

			case 3:
				// Update (Update lease details)
				System.out.print("Enter lease ID to update: ");
				long updateLeaseId = scanner.nextLong();
				Lease updateLease = leaseDao.getLeaseById(updateLeaseId);

				if (updateLease != null) {
					System.out.print("Enter new monthly rent: ");
					updateLease.setMonthlyRent(scanner.nextDouble());
					leaseDao.updateLease(updateLease);
					System.out.println("Lease updated successfully!");
				} else {
					System.out.println("Lease not found!");
				}
				break;

			case 4:
				// Delete (Delete lease)
				System.out.print("Enter lease ID to delete: ");
				long deleteLeaseId = scanner.nextLong();
				leaseDao.deleteLease(deleteLeaseId);
				System.out.println("Lease deleted successfully!");
				break;

			case 5:
				// List All Users
				List<Lease> leaseList = leaseDao.getAllLease();
				for (Lease lease : leaseList) {
					System.out.println(lease);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 6.");
			}

		}
	}

	private static void performBookingRequestCrudOperations() {
		// Implement CRUD operations for Booking Request
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add booking request)");
			System.out.println("2. Read (Get booking request by ID)");
			System.out.println("3. Update (Update booking request details)");
			System.out.println("4. Delete (Delete booking request)");
			System.out.println("5. List All BookingRequest");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add booking request)
				BookingRequest newBookingRequest = new BookingRequest();
				System.out.print("Enter Property ID (existing property): ");
				long propertyID = scanner.nextLong();

				// For simplicity, let's assume you have a Property object available
				// You can fetch the Property from the database based on user input
				// and set it to the newBookingRequest
				Property property = new Property();
				property.setPropertyId(propertyID);
				newBookingRequest.setProperty(property);

				System.out.print("Enter Tenant ID (existing tenant): ");
				int tenantID = scanner.nextInt();

				// For simplicity, let's assume you have a Tenant object available
				// You can fetch the Tenant from the database based on user input
				// and set it to the newBookingRequest
				Tenant tenant = new Tenant();
				tenant.setTenantId(tenantID);
				newBookingRequest.setTenant(tenant);

				// Handling Date input
				try {
					System.out.print("Enter request date (yyyy-MM-dd): ");
					String requestDateStr = scanner.next();
					Date requestDate = new SimpleDateFormat("yyyy-MM-dd").parse(requestDateStr);
					newBookingRequest.setRequestDate(requestDate);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
					break;
				}

				System.out.print("Enter status (Pending, Approved, Rejected): ");
				newBookingRequest.setStatus(scanner.next());

				bookingRequestDao.addBookingRequest(newBookingRequest);
				System.out.println("Booking request added successfully!");
				break;

			case 2:
				// Read (Get booking request by ID)
				System.out.print("Enter booking request ID to retrieve: ");
				long requestId = scanner.nextLong();
				BookingRequest retrievedBookingRequest = bookingRequestDao.getBookingRequestById(requestId);
				System.out.println("Retrieved Booking Request: " + retrievedBookingRequest);
				break;

			case 3:
				// Update (Update booking request details)
				System.out.print("Enter booking request ID to update: ");
				long updateRequestId = scanner.nextLong();
				BookingRequest updateBookingRequest = bookingRequestDao.getBookingRequestById(updateRequestId);

				if (updateBookingRequest != null) {
					System.out.print("Enter new status (Pending, Approved, Rejected): ");
					updateBookingRequest.setStatus(scanner.next());
					bookingRequestDao.updateBookingRequest(updateBookingRequest);
					System.out.println("Booking request updated successfully!");
				} else {
					System.out.println("Booking request not found!");
				}
				break;

			case 4:
				// Delete (Delete booking request)
				System.out.print("Enter booking request ID to delete: ");
				long deleteRequestId = scanner.nextLong();
				bookingRequestDao.deleteBookingRequest(deleteRequestId);
				System.out.println("Booking request deleted successfully!");
				break;

			case 5:
				// List All Users
				List<BookingRequest> bookingreqList = bookingRequestDao.getAllRequest();
				for (BookingRequest request : bookingreqList) {
					System.out.println(request);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 6.");
			}

		}
	}

	private static void performPaymentCrudOperations() {
		// Implement CRUD operations for Payment
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("CRUD Operations:");
			System.out.println("1. Create (Add payment)");
			System.out.println("2. Read (Get payment by ID)");
			System.out.println("3. Update (Update payment details)");
			System.out.println("4. Delete (Delete payment)");
			System.out.println("5. List All PaymentDetails");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Create (Add payment)
				Payment newPayment = new Payment();
				System.out.print("Enter Lease ID (existing lease): ");
				long leaseID = scanner.nextLong();

				// For simplicity, let's assume you have a Lease object available
				// You can fetch the Lease from the database based on user input
				// and set it to the newPayment
				Lease lease = new Lease();
				lease.setLeaseId(leaseID);
				newPayment.setLease(lease);

				// Handling Date input
				try {
					System.out.print("Enter payment date (yyyy-MM-dd): ");
					String paymentDateStr = scanner.next();
					Date paymentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paymentDateStr);
					newPayment.setPaymentDate(paymentDate);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
					break;
				}

				System.out.print("Enter payment amount: ");
				newPayment.setAmount(scanner.nextDouble());
				System.out.print("Enter payment method: ");
				newPayment.setPaymentMethod(scanner.next());

				paymentDao.addPayment(newPayment);
				System.out.println("Payment added successfully!");
				break;

			case 2:
				// Read (Get payment by ID)
				System.out.print("Enter payment ID to retrieve: ");
				long paymentId = scanner.nextLong();
				Payment retrievedPayment = paymentDao.getPaymentById(paymentId);
				System.out.println("Retrieved Payment: " + retrievedPayment);
				break;

			case 3:
				// Update (Update payment details)
				System.out.print("Enter payment ID to update: ");
				long updatePaymentId = scanner.nextLong();
				Payment updatePayment = paymentDao.getPaymentById(updatePaymentId);

				if (updatePayment != null) {
					System.out.print("Enter new payment amount: ");
					updatePayment.setAmount(scanner.nextDouble());
					paymentDao.updatePayment(updatePayment);
					System.out.println("Payment updated successfully!");
				} else {
					System.out.println("Payment not found!");
				}
				break;

			case 4:
				// Delete (Delete payment)
				System.out.print("Enter payment ID to delete: ");
				long deletePaymentId = scanner.nextLong();
				paymentDao.deletePayment(deletePaymentId);
				System.out.println("Payment deleted successfully!");
				break;
			case 5:
				// List All Users
				List<Payment> paymentList = paymentDao.getAllPaymentlist();
				for (Payment payment : paymentList) {
					System.out.println(payment);
				}
				break;

			case 6:
				// Back to Main Menu
				return;

			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 6.");
			}

		}
	}
}
