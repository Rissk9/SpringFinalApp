package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.DTO.custDetailDTO;
import com.example.demo.DTO.custIndentifDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer_Detail")
public class CustomerDetail {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="Customer_Identifier")
	private Long customerId;
	
	@Column(name="Customer_FullName")
	private String custFullname;
	
	@Column(name = "Customer_Gender")
	private String custGender;
	
	@Column(name = "Customer_Type")
	private LocalDate custDate;
	
	@Column(name = "Customer_PreferredLanguage")
	private String custPrefLanguage;
	
	@Column(name = "Customer_Status")
	private String custStatus;
	
	@Column(name = "Customer_CountryOfOrigin")
	private String cust_country;
	
	@Column(name= "CRUD_Value")
	private Character crudval='C';
	
	@ManyToOne
	@JoinColumn(name="Customer_Classification_Id", nullable = false)
	private CustomerClassificationType classificationType;
	

	@OneToOne(mappedBy = "customerDetail_FK", cascade = CascadeType.ALL, orphanRemoval = true)
	private CustomerIdentification customerIdentification;
	
	@OneToMany(mappedBy = "customerDetail_FK_Custname", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerName> customerNames;
	
	@OneToMany(mappedBy = "customerDetail_fk_proofofId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerProofofId> customerProofofIds;
	
	@OneToMany(mappedBy = "customerDetail_FK_custContact",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerContactInformation> customerContactInformations;
	
	@OneToMany(mappedBy = "customerDetail_FK_custAddress", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerAddress> customerAddresses;
	
	
	
	public CustomerDetail() {
		super();
	}
	
	

	public CustomerDetail(Long customerId, String custFullname, String custGender, LocalDate custDate,
			String custPrefLanguage, String custStatus, String cust_country,
			CustomerClassificationType classificationType, CustomerIdentification customerIdentification,
			List<CustomerName> customerNames, List<CustomerProofofId> customerProofofIds,
			List<CustomerContactInformation> customerContactInformations, List<CustomerAddress> customerAddresses) {
		super();
		this.customerId = customerId;
		this.custFullname = custFullname;
		this.custGender = custGender;
		this.custDate = custDate;
		this.custPrefLanguage = custPrefLanguage;
		this.custStatus = custStatus;
		this.cust_country = cust_country;
		this.classificationType = classificationType;
		this.customerIdentification = customerIdentification;
		this.customerNames = customerNames;
		this.customerProofofIds = customerProofofIds;
		this.customerContactInformations = customerContactInformations;
		this.customerAddresses = customerAddresses;
	}



	public List<CustomerAddress> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	
	
	//--------------------------------Getters and setters---------------------------------------------
	
	
	public List<CustomerContactInformation> getCustomerContactInformations() {
		return customerContactInformations;
	}


	public Character getCrudval() {
		return crudval;
	}



	public void setCrudval(Character crudval) {
		this.crudval = crudval;
	}



	public void setCustomerContactInformations(List<CustomerContactInformation> customerContactInformations) {
		this.customerContactInformations = customerContactInformations;
	}

	public List<CustomerProofofId> getCustomerProofofIds() {
		return customerProofofIds;
	}

	public void setCustomerProofofIds(List<CustomerProofofId> customerProofofIds) {
		this.customerProofofIds = customerProofofIds;
	}

	public CustomerIdentification getCustomerIdentification() {
		return customerIdentification;
	}

	public void setCustomerIdentification(CustomerIdentification customerIdentification) {
		this.customerIdentification = customerIdentification;
	}

	public List<CustomerName> getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(List<CustomerName> customerNames) {
		this.customerNames = customerNames;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustFullname() {
		return custFullname;
	}

	public void setCustFullname(String custFullname) {
		this.custFullname = custFullname;
	}

	public String getCustGender() {
		return custGender;
	}

	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}

	public LocalDate getCustDate() {
		return custDate;
	}

	public void setCustDate(LocalDate custDate) {
		this.custDate = custDate;
	}

	public String getCustPrefLanguage() {
		return custPrefLanguage;
	}

	public void setCustPrefLanguage(String custPrefLanguage) {
		this.custPrefLanguage = custPrefLanguage;
	}

	public String getCustStatus() {
		return custStatus;
	}

	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	public String getCust_country() {
		return cust_country;
	}

	public void setCust_country(String cust_country) {
		this.cust_country = cust_country;
	}

	public CustomerClassificationType getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(CustomerClassificationType classificationType) {
		this.classificationType = classificationType;
	}
	
	
	
	
	
	
	
}
