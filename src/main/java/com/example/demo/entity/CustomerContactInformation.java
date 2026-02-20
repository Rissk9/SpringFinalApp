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
@Table(name = "CustomerContactInfo")
public class CustomerContactInformation {

	@Id
	@Column(name = "custContactInfoID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custContactId;
	
	@ManyToOne
	@JoinColumn(name = "Customer_Identifier")
	private CustomerDetail customerDetail_FK_custContact;
	
	@Column(name = "Customer_Contact_Type")
	private String customerContactType;
	
	@Column(name = "Customer_Contact_Value")
	private String customerContactValue;
	
	@Column(name = "Effective_Date")
	private LocalDate effectiveDate;
	
	@Column(name = "Start_Date")
	private LocalDate startDate;
	
	@Column(name = "End_Date")
	private LocalDate endDate;
	
	@Column(name= "CRUD_Value")
	private Character crudval='C';

	
	
	public CustomerDetail getCustomerDetail_FK_custContact() {
		return customerDetail_FK_custContact;
	}

	public void setCustomerDetail_FK_custContact(CustomerDetail customerDetail_FK_custContact) {
		this.customerDetail_FK_custContact = customerDetail_FK_custContact;
	}

	public Character getCrudval() {
		return crudval;
	}

	public void setCrudval(Character crudval) {
		this.crudval = crudval;
	}

	public void setCrudval(char crudval) {
		this.crudval = crudval;
	}

	public Long getCustContactId() {
		return custContactId;
	}

	public void setCustContactId(Long custContactId) {
		this.custContactId = custContactId;
	}

	public CustomerDetail getCustomerDetail() {
		return customerDetail_FK_custContact;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail_FK_custContact = customerDetail;
	}

	public String getCustomerContactType() {
		return customerContactType;
	}

	public void setCustomerContactType(String customerContactType) {
		this.customerContactType = customerContactType;
	}

	public String getCustomerContactValue() {
		return customerContactValue;
	}

	public void setCustomerContactValue(String customerContactValue) {
		this.customerContactValue = customerContactValue;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public CustomerContactInformation(Long custContactId, CustomerDetail customerDetail_FK_custContact,
			String customerContactType, String customerContactValue, LocalDate effectiveDate, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.custContactId = custContactId;
		this.customerDetail_FK_custContact = customerDetail_FK_custContact;
		this.customerContactType = customerContactType;
		this.customerContactValue = customerContactValue;
		this.effectiveDate = effectiveDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public CustomerContactInformation() {
	}
	
	
	
	
	
	
	
	
	
}
