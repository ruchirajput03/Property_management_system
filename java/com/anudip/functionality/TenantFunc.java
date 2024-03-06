package com.anudip.functionality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.anudip.daoclasses.BookingRequestDAO;
import com.anudip.daoclasses.PaymentDAO;
import com.anudip.daoclasses.PropertyDAO;
import com.anudip.entityclasses.BookingRequest;
import com.anudip.entityclasses.Lease;
import com.anudip.entityclasses.Payment;
import com.anudip.entityclasses.Property;
import com.anudip.entityclasses.Tenant;

public class TenantFunc {

	private static PropertyDAO propertyDao = new PropertyDAO();
	private static BookingRequestDAO bookingRequestDao = new BookingRequestDAO();
	private static PaymentDAO paymentDao = new PaymentDAO();

	public void tenantFunc() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Select an option:");
			System.out.println("1. Add Booking Request");
			System.out.println("2. Make Payment");
			System.out.println("3. View Property");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				addBookingRequest(scanner);
				break;
			case 2:
				makePayment(scanner);
				break;
			case 3:
				viewProperty();
				break;
			case 4:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 4.");
			}
		}
	}

	private static void addBookingRequest(Scanner scanner) {
		BookingRequest newBookingRequest = new BookingRequest();
		System.out.print("Enter Property ID (existing property): ");
		long propertyID = scanner.nextLong();

		Property property = new Property();
		property.setPropertyId(propertyID);
		newBookingRequest.setProperty(property);

		System.out.print("Enter Tenant ID (existing tenant): ");
		int tenantID = scanner.nextInt();

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
		}

		System.out.print("Enter status (Pending, Approved, Rejected): ");
		System.out.print("User has to type Pending");
		newBookingRequest.setStatus(scanner.next());

		bookingRequestDao.addBookingRequest(newBookingRequest);
		System.out.println("Booking request added successfully!");
		System.out.println("Booking request added successfully!");
	}

	@SuppressWarnings("unused")
	private static void makePayment(Scanner scanner) {
		Payment newPayment = new Payment();
		System.out.print("Enter Lease ID (existing lease): ");
		long leaseID = scanner.nextLong();

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
		}

		System.out.print("Enter payment method: ");
		newPayment.setPaymentMethod(scanner.next());
		if ("Online".equalsIgnoreCase(newPayment.getPaymentMethod())) {
			System.out.println("Enter debit card number:");
			String cardNumber = scanner.nextLine();

			System.out.println("Enter expiry date (MM/YYYY):");
			String expiryDate = scanner.nextLine();

			System.out.println("Enter CVV:");
			String cvv = scanner.nextLine();
			System.out.print("Enter payment amount: ");
			double Amount = scanner.nextDouble();
			while (true) {
				System.out.print("1: Confirm Payment");
				System.out.print("2: Cancel");
				System.out.print("3: Exit");
				int select = scanner.nextInt();
				switch (select) {
				case 1:
					newPayment.setAmount(Amount);
					System.out.println("Payment successful!");
					paymentDao.addPayment(newPayment);
					break;
				case 2:
					newPayment.setAmount(0);
					System.out.println("Payment Failed");
					break;
				case 3:
					System.out.println("Exiting without payment");
					System.exit(0);
					break;
				default:
					System.out.println("Enter your choice between 1 and 3");
				}
			}
		} else if ("Cash".equalsIgnoreCase(newPayment.getPaymentMethod())) {
			newPayment.setAmount(0);
			System.out.println("Kindly Pay at the Property ThankYou!");
			paymentDao.addPayment(newPayment);
		} else {
			System.out.println("Select Method of Pay");
		}
	}

	private static void viewProperty() {
		List<Property> propertyList = propertyDao.getAllProperty();
		System.out.println("Property Details: ");
		for (Property property : propertyList) {
			System.out.println(property);
		}

	}
}
