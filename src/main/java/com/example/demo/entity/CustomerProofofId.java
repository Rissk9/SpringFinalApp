package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CustomerProofOfID")
public class CustomerProofofId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerProofofID_id")
	private long customerProofId;
	
	@ManyToOne
	@JoinColumn(name = "Customer_Identifier", nullable = false)
	private CustomerDetail customerDetail_fk_proofofId;
	
	@Column(name = "Cust_proof_of_idType")
	private String proofofIdType;
	
	@Column(name = "Cust_proof_of_idValue")
	private String proofofIdValue;
	
	@Column(name = "Effective_Date")
	private LocalDate effectivDate;
	
	@Column(name = "Start_Date")
	private LocalDate startDate;
	
	@Column(name = "End_Date")
	private LocalDate endDate;

	
	public CustomerProofofId(Long customerProofId, CustomerDetail customerDetail_fk_proofofId, String proofofIdType,
			String proofofIdValue, LocalDate effectivDate, LocalDate startDate, LocalDate endDate) {
		super();
		this.customerProofId = customerProofId;
		this.customerDetail_fk_proofofId = customerDetail_fk_proofofId;
		this.proofofIdType = proofofIdType;
		this.proofofIdValue = proofofIdValue;
		this.effectivDate = effectivDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public CustomerProofofId() {
		super();
	}

	public long getCustomerProofId() {
		return customerProofId;
	}

	public void setCustomerProofId(long customerProofId) {
		this.customerProofId = customerProofId;
	}

	public CustomerDetail getCustomerDetail_fk_proofofId() {
		return customerDetail_fk_proofofId;
	}

	public void setCustomerDetail_fk_proofofId(CustomerDetail customerDetail_fk_proofofId) {
		this.customerDetail_fk_proofofId = customerDetail_fk_proofofId;
	}

	public String getProofofIdType() {
		return proofofIdType;
	}

	public void setProofofIdType(String proofofIdType) {
		this.proofofIdType = proofofIdType;
	}

	public String getProofofIdValue() {
		return proofofIdValue;
	}

	public void setProofofIdValue(String proofofIdValue) {
		this.proofofIdValue = proofofIdValue;
	}

	public LocalDate getEffectivDate() {
		return effectivDate;
	}

	public void setEffectivDate(LocalDate effectivDate) {
		this.effectivDate = effectivDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
