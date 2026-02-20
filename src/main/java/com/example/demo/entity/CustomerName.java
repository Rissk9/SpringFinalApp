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
@Table(name = "Customer_names")
public class CustomerName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerName_Id")
	private Long custNameId;
	
	@ManyToOne
	@JoinColumn(name = "Customer_Identifier", nullable = false)
	private CustomerDetail customerDetail_FK_Custname;
	
	@ManyToOne
    @JoinColumn(name = "Customer_classification_id", nullable = false)
    private CustomerClassificationType classificationType_FK_Custname;
	
	@Column(name = "customer_name_type")
	private String customerNameType;
	 
	@Column(name = "customer_name_value")
    private String customerNameValue;
	
	@Column(name = "effective_date")
    private LocalDate effectiveDate;
	
	@Column(name= "CRUD_Value")
	private Character crudval='C';
	
	

	

	public Character getCrudval() {
		return crudval;
	}

	public void setCrudval(Character crudval) {
		this.crudval = crudval;
	}

	public Long getCustNameId() {
		return custNameId;
	}

	public void setCustNameId(Long custNameId) {
		this.custNameId = custNameId;
	}

	public CustomerDetail getCustomerDetail_FK_Custname() {
		return customerDetail_FK_Custname;
	}

	public void setCustomerDetail_FK_Custname(CustomerDetail customerDetail_FK_Custname) {
		this.customerDetail_FK_Custname = customerDetail_FK_Custname;
	}

	public CustomerClassificationType getClassificationType_FK_Custname() {
		return classificationType_FK_Custname;
	}

	public void setClassificationType_FK_Custname(CustomerClassificationType classificationType_FK_Custname) {
		this.classificationType_FK_Custname = classificationType_FK_Custname;
	}

	public String getCustomerNameType() {
		return customerNameType;
	}

	public void setCustomerNameType(String customerNameType) {
		this.customerNameType = customerNameType;
	}

	public String getCustomerNameValue() {
		return customerNameValue;
	}

	public void setCustomerNameValue(String customerNameValue) {
		this.customerNameValue = customerNameValue;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public CustomerName(Long custNameId, CustomerDetail customerDetail_FK_Custname,
			CustomerClassificationType classificationType_FK_Custname, String customerNameType,
			String customerNameValue, LocalDate effectiveDate) {
		super();
		this.custNameId = custNameId;
		this.customerDetail_FK_Custname = customerDetail_FK_Custname;
		this.classificationType_FK_Custname = classificationType_FK_Custname;
		this.customerNameType = customerNameType;
		this.customerNameValue = customerNameValue;
		this.effectiveDate = effectiveDate;
	}

	public CustomerName() {
		super();
	} 

	
	
	
	
	
}
