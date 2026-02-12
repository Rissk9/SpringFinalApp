package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.DTO.custIndentifDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer_Identification")
public class CustomerIdentification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "Customer_Identification_ID")
	private Long customerIdentifId;
	
	@Column(name = "Customer_Identification_Type")
	private String custIdentificationtype;
	
	@OneToOne
	@JoinColumn(name = "Customer_Identifier", nullable = false)
	private CustomerDetail customerDetail_FK;
	
	@Column(name = "Customer_Identification_Item")
	private String custIdentificationItem;
	
	@Column(name="Effective_Date")
	private LocalDate effectiveDate;
	
	
	
	

	public CustomerIdentification(Long customerIdentifId, String custIdentificationtype,
			CustomerDetail customerDetail_FK, String custIdentificationItem, LocalDate effectiveDate) {
		super();
		this.customerIdentifId = customerIdentifId;
		this.custIdentificationtype = custIdentificationtype;
		this.customerDetail_FK = customerDetail_FK;
		this.custIdentificationItem = custIdentificationItem;
		this.effectiveDate = effectiveDate;
	}


	public CustomerIdentification() {
		super();
	}


	public Long getCustomerIdentifId() {
		return customerIdentifId;
	}

	public void setCustomerIdentifId(Long customerIdentifId) {
		this.customerIdentifId = customerIdentifId;
	}

	public CustomerDetail getCustomerDetail_FK() {
		return customerDetail_FK;
	}

	public void setCustomerDetail_FK(CustomerDetail customerDetail_FK) {
		this.customerDetail_FK = customerDetail_FK;
	}

	public String getCustIdentificationtype() {
		return custIdentificationtype;
	}

	public void setCustIdentificationtype(String custIdentificationtype) {
		this.custIdentificationtype = custIdentificationtype;
	}

	public String getCustIdentificationItem() {
		return custIdentificationItem;
	}

	public void setCustIdentificationItem(String custIdentificationItem) {
		this.custIdentificationItem = custIdentificationItem;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	
}
