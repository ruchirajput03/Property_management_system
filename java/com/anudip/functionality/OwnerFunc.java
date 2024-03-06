package com.anudip.functionality;

import com.anudip.daoclasses.BookingRequestDAO;
import com.anudip.daoclasses.LeaseDAO;
import com.anudip.daoclasses.PaymentDAO;
import com.anudip.daoclasses.PropertyDAO;
import com.anudip.daoclasses.TenantDAO;
import com.anudip.entityclasses.BookingRequest;
import com.anudip.entityclasses.Lease;
import com.anudip.entityclasses.Owner;
import com.anudip.entityclasses.Payment;
import com.anudip.entityclasses.Property;
import com.anudip.entityclasses.Tenant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OwnerFunc {

	private static TenantDAO tenantDao = new TenantDAO();
	private static PropertyDAO propertyDao = new PropertyDAO();
	private static PaymentDAO paymentDao = new PaymentDAO();
	private static BookingRequestDAO bookingRequestDao = new BookingRequestDAO();
	private static LeaseDAO leaseDao = new LeaseDAO();
	Scanner scanner = new Scanner(System.in);

	public void OwnerFunctions() {

		while (true) {
			System.out.println("1. Add Property");
			System.out.println("2. Update Property");
			System.out.println("3. View Properties");
			System.out.println("4. Delete Property");
			System.out.println("5. View Booking Requests");
			System.out.println("6. Update Booking Requests");
			System.out.println("7. View Tenants");
			System.out.println("8. View Payments");
			System.out.println("9. Add Lease Details");
			System.out.println("10. Update Lease Details");
			System.out.println("11. Delete Lease Details");
			System.out.println("12. View Lease Details");
			System.out.println("13. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline

			switch (choice) {
			case 1:
				addProperty();
				break;
			case 2:
				updateProperty();
				break;
			case 3:
				viewProperties();
				break;
			case 4:
				deleteProperty();
				break;
			case 5:
				viewBookingRequests();
				break;
			case 6:
				updateBookingRequests();
				break;
			case 7:
				viewTenants();
				break;
			case 8:
				viewPayments();
				break;
			case 9:
				addLease();
				break;
			case 10:
				deleteLease();
				break;
			case 11:
				updateLease();
				break;
			case 12:
				viewLease();
				break;
			case 13:
				System.out.println("Exiting Owner Functions.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}

		}

	}

	private void viewLease() {
		List<Lease> leaseList = leaseDao.getAllLease();
		for (Lease lease : leaseList) {
			System.out.println(lease);
		}
	}

	private void updateLease() {

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

	}

	private void deleteLease() {
		System.out.print("Enter lease ID to delete: ");
		long deleteLeaseId = scanner.nextLong();
		leaseDao.deleteLease(deleteLeaseId);
		System.out.println("Lease deleted successfully!");
	}

	private void addLease() {
		Lease newLease = new Lease();
		System.out.print("Enter Tenant ID (existing tenant): ");
		int tenantID = scanner.nextInt();

		Tenant tenant = new Tenant();
		tenant.setTenantId(tenantID);
		newLease.setTenant(tenant);

		System.out.print("Enter Property ID (existing property): ");
		long propertyID = scanner.nextLong();

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
		}

		System.out.print("Enter monthly rent: ");
		newLease.setMonthlyRent(scanner.nextDouble());
		System.out.print("Enter lease term (in months): ");
		newLease.setLeaseTerm(scanner.nextInt());
		System.out.print("Enter security deposit: ");
		newLease.setSecurityDeposit(scanner.nextDouble());

		leaseDao.addLease(newLease);
		System.out.println("Lease added successfully!");
	}

	private void updateBookingRequests() {
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

	}

	private void deleteProperty() {
		System.out.print("Enter property ID to delete: ");
		long deletePropertyId = scanner.nextLong();
		propertyDao.deleteProperty(deletePropertyId);
		System.out.println("Property deleted successfully!");

	}

	private void addProperty() {
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
		scanner.close();
	}

	private void updateProperty() {
		System.out.print("Enter property ID to update: ");
		long updatePropertyId = scanner.nextLong();
		Property updateProperty = propertyDao.getPropertyById(updatePropertyId);
		scanner.close();
		if (updateProperty != null) {
			System.out.print("Enter new rental fees: ");
			updateProperty.setRentalFees(scanner.nextDouble());
			propertyDao.updateProperty(updateProperty);
			System.out.println("Property updated successfully!");
		} else {
			System.out.println("Property not found!");
		}
	}

	private void viewProperties() {
		List<Property> propertyList = propertyDao.getAllProperty();
		for (Property property : propertyList) {
			System.out.println(property);
		}

	}

	private void viewBookingRequests() {
		List<BookingRequest> bookingreqList = bookingRequestDao.getAllRequest();
		for (BookingRequest request : bookingreqList) {
			System.out.println(request);
		}
	}

	private void viewTenants() {
		List<Tenant> tenantList = tenantDao.getAllTenant();
		for (Tenant tenant : tenantList) {
			System.out.println(tenant);
		}
	}

	private void viewPayments() {
		System.out.print("Enter payment ID to retrieve: ");
		long paymentId = scanner.nextLong();
		Payment retrievedPayment = paymentDao.getPaymentById(paymentId);
		System.out.println("Retrieved Payment: " + retrievedPayment);
	}
}
