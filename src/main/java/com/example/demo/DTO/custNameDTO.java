package com.example.demo.DTO;

import java.time.LocalDate;

public class custNameDTO {

	private Long custNameId;
	private String customerNameType;
    private String customerNameValue;
    private LocalDate effectiveDate;
    private Long custId;
    private Long classificationId;
    
    
    
	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Long classificationId) {
		this.classificationId = classificationId;
	}

	public custNameDTO() {
		super();
	}
	
	public Long getCustNameId() {
		return custNameId;
	}
	public void setCustNameId(Long custNameId) {
		this.custNameId = custNameId;
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
	public custNameDTO(Long custNameId, String customerNameType, String customerNameValue, LocalDate effectiveDate) {
		super();
		this.custNameId = custNameId;
		this.customerNameType = customerNameType;
		this.customerNameValue = customerNameValue;
		this.effectiveDate = effectiveDate;
	}

    

}
