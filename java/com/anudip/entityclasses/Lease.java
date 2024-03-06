package com.anudip.entityclasses;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Lease")
public class Lease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LeaseID")
	private Long leaseId;

	@ManyToOne
	@JoinColumn(name = "TenantID")
	private Tenant tenant;

	@ManyToOne
	@JoinColumn(name = "PropertyID")
	private Property property;

	@Column(name = "LeaseStartDate")
	@Temporal(TemporalType.DATE)
	private Date leaseStartDate;

	@Column(name = "LeaseEndDate")
	@Temporal(TemporalType.DATE)
	private Date leaseEndDate;

	@Column(name = "MonthlyRent")
	private double monthlyRent;

	@Column(name = "LeaseTerm")
	private int leaseTerm;

	@Column(name = "SecurityDeposit")
	private double securityDeposit;

	// Constructors, getters, setters, and other methods

	public Lease() {
		super();
	}

	public Lease(Long leaseId, Tenant tenant, Property property, Date leaseStartDate, Date leaseEndDate,
			double monthlyRent, int leaseTerm, double securityDeposit) {
		super();
		this.leaseId = leaseId;
		this.tenant = tenant;
		this.property = property;
		this.leaseStartDate = leaseStartDate;
		this.leaseEndDate = leaseEndDate;
		this.monthlyRent = monthlyRent;
		this.leaseTerm = leaseTerm;
		this.securityDeposit = securityDeposit;
	}

	public Long getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(Long leaseId) {
		this.leaseId = leaseId;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
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

	public double getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(double monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public int getLeaseTerm() {
		return leaseTerm;
	}

	public void setLeaseTerm(int leaseTerm) {
		this.leaseTerm = leaseTerm;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	@Override
	public String toString() {
		return "leaseId=" + this.leaseId + ", tenant=" + this.tenant + ", leaseStartDate=" + this.leaseStartDate
				+ ", leaseEndDate=" + this.leaseEndDate + ", monthlyRent=" + this.monthlyRent + ", leaseTerm="
				+ this.leaseTerm + ", securityDeposit=" + this.securityDeposit;
	}

}
