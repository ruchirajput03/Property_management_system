package com.anudip.entityclasses;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentID")
	private Long paymentId;

	@ManyToOne
	@JoinColumn(name = "LeaseID")
	private Lease lease;

	@Column(name = "PaymentDate")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	@Column(name = "Amount")
	private double amount;

	@Column(name = "PaymentMethod")
	private String paymentMethod;

	public Payment() {
		super();
	}

	public Payment(Long paymentId, Lease lease, Date paymentDate, double amount, String paymentMethod) {
		super();
		this.paymentId = paymentId;
		this.lease = lease;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Lease getLease() {
		return lease;
	}

	public void setLease(Lease lease) {
		this.lease = lease;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "paymentId=" + this.paymentId + ", paymentDate=" + this.paymentDate + ", amount=" + this.amount
				+ ", paymentMethod=" + this.paymentMethod;
	}

}
