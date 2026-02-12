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
@Table(name = "CustomerAddress")
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Customer_Address_Id")
	private Long custAddressId;
	
	@ManyToOne
	@JoinColumn(name = "Customer_Identifier")
	private CustomerDetail customerDetail_FK_custAddress;
	
	
	@ManyToOne
	@JoinColumn(name = "Customer_classification_id")
	private CustomerClassificationType customerClassificationType_FK_custAddress;
	
	@Column(name = "Customer_Address_type")
	private String CustomerAddressType;
	
	@Column(name = "Customer_Address_Value")
	private String CustomerAddressValue;
	
	@Column(name = "Effective_Date")
	private LocalDate effectiveDate;

	
	public CustomerAddress() {
		super();
	}

	public CustomerAddress(Long custAddressId, CustomerDetail customerDetail_FK_custAddress,
			CustomerClassificationType customerClassificationType_FK_custAddress, String customerAddressType,
			String customerAddressValue, LocalDate effectiveDate) {
		super();
		this.custAddressId = custAddressId;
		this.customerDetail_FK_custAddress = customerDetail_FK_custAddress;
		this.customerClassificationType_FK_custAddress = customerClassificationType_FK_custAddress;
		CustomerAddressType = customerAddressType;
		CustomerAddressValue = customerAddressValue;
		this.effectiveDate = effectiveDate;
	}

	public Long getCustAddressId() {
		return custAddressId;
	}

	public void setCustAddressId(Long custAddressId) {
		this.custAddressId = custAddressId;
	}

	public CustomerDetail getCustomerDetail_FK_custAddress() {
		return customerDetail_FK_custAddress;
	}

	public void setCustomerDetail_FK_custAddress(CustomerDetail customerDetail_FK_custAddress) {
		this.customerDetail_FK_custAddress = customerDetail_FK_custAddress;
	}

	public CustomerClassificationType getCustomerClassificationType_FK_custAddress() {
		return customerClassificationType_FK_custAddress;
	}

	public void setCustomerClassificationType_FK_custAddress(
			CustomerClassificationType customerClassificationType_FK_custAddress) {
		this.customerClassificationType_FK_custAddress = customerClassificationType_FK_custAddress;
	}

	public String getCustomerAddressType() {
		return CustomerAddressType;
	}

	public void setCustomerAddressType(String customerAddressType) {
		CustomerAddressType = customerAddressType;
	}

	public String getCustomerAddressValue() {
		return CustomerAddressValue;
	}

	public void setCustomerAddressValue(String customerAddressValue) {
		CustomerAddressValue = customerAddressValue;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	
}
