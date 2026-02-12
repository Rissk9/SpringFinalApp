package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.CustomerAddress;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerName;

public class custClassifDTO {

	private Long customerClassificationId;
	private String customerClassificationType;
	private String customerClassificationValue;
	private LocalDate effectiveDate;
	
	private List<custDetailDTO> customerDetails;
	private List<custNameDTO> customerNames;
	private List<custAddressDTO> customerAddresses;
	
	//one default constructor is compulsarily needed
	public custClassifDTO() {
		super();
	}
	
	public Long getCustomerClassificationId() {
		return customerClassificationId;
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
	

	public List<custDetailDTO> getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(List<custDetailDTO> customerDetails) {
		this.customerDetails = customerDetails;
	}

	public List<custNameDTO> getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(List<custNameDTO> customerNames) {
		this.customerNames = customerNames;
	}

	public List<custAddressDTO> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(List<custAddressDTO> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public custClassifDTO(Long customerClassificationId, String customerClassificationType,
			String customerClassificationValue, LocalDate effectiveDate, List<custDetailDTO> customerDetails,
			List<custNameDTO> customerNames, List<custAddressDTO> customerAddresses) {
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
