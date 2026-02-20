package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.DTO.custClassifDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_classification_type")
public class CustomerClassificationType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Customer_classification_id")
	private Long customerClassificationId;
	
	@Column(name = "Customer_classification_type")
	private String customerClassificationType;
	
	@Column(name= "Customer_classification_value")
	private String customerClassificationValue;
	
	@Column(name = "Effective_Date")
	private LocalDate effectiveDate;
	
	@Column(name= "CRUD_Value")
	private Character crudval='C';
	
	// mappedBy contains the column that will contain the primary key of this table so basically the column of the foreign key
//	@JsonIgnore
	@JsonIgnore
	@OneToMany(mappedBy = "classificationType",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerDetail> customerDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "classificationType_FK_Custname", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerName> customerNames;

	@JsonIgnore
	@OneToMany(mappedBy = "customerClassificationType_FK_custAddress", cascade = CascadeType.ALL, orphanRemoval = true)
	
	
	private List<CustomerAddress> customerAddresses;
	//getters and setters
	
	

	public List<CustomerAddress> getCustomerAddresses() {
		return customerAddresses;
	}

	public Character getCrudval() {
		return crudval;
	}

	public void setCrudval(Character crudval) {
		this.crudval = crudval;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public Long getCustomerClassificationId() {
		return customerClassificationId;
	}

	public List<CustomerName> getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(List<CustomerName> customerNames) {
		this.customerNames = customerNames;
	}

	public void setCustomerClassificationId(Long customerClassificationId) {
		this.customerClassificationId = customerClassificationId;
	}

	public String getCustomerClassificationType() {
		return customerClassificationType;
	}

	public void setCustomerClassificationType(String customerClassificationType) {
		this.customerClassificationType = customerClassificationType;
	}

	public String getCustomerClassificationValue() {
		return customerClassificationValue;
	}

	public void setCustomerClassificationValue(String customerClassificationValue) {
		this.customerClassificationValue = customerClassificationValue;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public List<CustomerDetail> getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(List<CustomerDetail> customerDetails) {
		this.customerDetails = customerDetails;
	}	
	
	
	public CustomerClassificationType() {
		super();
	}

	public CustomerClassificationType(Long customerClassificationId, String customerClassificationType,
			String customerClassificationValue, LocalDate effectiveDate, List<CustomerDetail> customerDetails,
			List<CustomerName> customerNames, List<CustomerAddress> customerAddresses) {
		super();
		this.customerClassificationId = customerClassificationId;
		this.customerClassificationType = customerClassificationType;
		this.customerClassificationValue = customerClassificationValue;
		this.effectiveDate = effectiveDate;
		this.customerDetails = customerDetails;
		this.customerNames = customerNames;
		this.customerAddresses = customerAddresses;
	}
	
	

}
