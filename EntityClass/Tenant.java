package com.anudip.EntityClass;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tenant")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TenantID")
    private int tenantId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(name = "LeaseStartDate")
    private Date leaseStartDate;

    @Column(name = "LeaseEndDate")
    private Date leaseEndDate;

    @Column(name = "EmergencyContactName")
    private String emergencyContactName;

    @ManyToOne
    @JoinColumn(name = "PropertyID")
    private Property property;

    // getters and setters

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLeaseStartDate() {
		return leaseStartDate;
	}

	public void setLeaseStartDate(Date leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}

	public Date getLeaseEndDate() {
		return leaseEndDate;
	}

	public void setLeaseEndDate(Date leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "tenantId=" + this.tenantId + ", firstName=" + this.firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + this.phone + ", leaseStartDate=" + this.leaseStartDate + ",leaseEndDate=" + leaseEndDate
				+ ", emergencyContactName=" + this.emergencyContactName ;
	}
}



