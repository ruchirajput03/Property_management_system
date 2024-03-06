package com.anudip.entityclasses;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PropertyID")
	private long propertyId;

	@ManyToOne
	@JoinColumn(name = "OwnerID")
	private Owner owner;

	@Column(name = "Address")
	private String address;

	@Column(name = "Description")
	private String description;

	@Column(name = "RentalFees")
	private double rentalFees;

	@Column(name = "AvailabilityStatus")
	private String availabilityStatus;

	public Property() {
		super();

	}

	public Property(long propertyId, Owner owner, String address, String description, double rentalFees,
			String availabilityStatus) {
		super();
		this.propertyId = propertyId;
		this.owner = owner;
		this.address = address;
		this.description = description;
		this.rentalFees = rentalFees;
		this.availabilityStatus = availabilityStatus;
	}

	// getters and setters
	public long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRentalFees() {
		return rentalFees;
	}

	public void setRentalFees(double rentalFees) {
		this.rentalFees = rentalFees;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	@Override
	public String toString() {
		return "propertyId=" + this.propertyId + ", address=" + this.address + ", description=" + this.description
				+ ", rentalFees=" + this.rentalFees + ", availabilityStatus=" + this.availabilityStatus;
	}

}
