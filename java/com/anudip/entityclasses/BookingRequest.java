package com.anudip.entityclasses;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "BookingRequest")
public class BookingRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequestID")
	private Long requestID;

	@ManyToOne
	@JoinColumn(name = "PropertyID")
	private Property property;

	@ManyToOne
	@JoinColumn(name = "TenantID")
	private Tenant tenant;

	@Column(name = "RequestDate")
	@Temporal(TemporalType.DATE)
	private Date requestDate;

	@Column(name = "Status")
	private String status; // Consider using an enum for better representation

	// Constructors, getters, setters, and other methods

	// Default constructor
	public BookingRequest() {
	}

	public BookingRequest(Long requestID, Property property, Tenant tenant, Date requestDate, String status) {
		super();
		this.requestID = requestID;
		this.property = property;
		this.tenant = tenant;
		this.requestDate = requestDate;
		this.status = status;
	}

	// Getters and Setters

	public Long getRequestID() {
		return requestID;
	}

	public void setRequestID(Long requestID) {
		this.requestID = requestID;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "requestID=" + this.requestID + ", property=" + this.property + ", tenant=" + this.tenant
				+ ", requestDate=" + this.requestDate + ", status=" + this.status;
	}

}
