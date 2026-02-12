package com.example.demo.DTO;

import java.time.LocalDate;

public class custContactInfoDTO {

	private Long custContactId;
	private String customerContactType;
	private String customerContactValue;
	private LocalDate effectiveDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long custId;

	
	
	
	public custContactInfoDTO() {
		super();
	}
	
	
	
	public Long getCustId() {
		return custId;
	}



	public void setCustId(Long custId) {
		this.custId = custId;
	}



	public Long getCustContactId() {
		return custContactId;
	}
	public void setCustContactId(Long custContactId) {
		this.custContactId = custContactId;
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
	public custContactInfoDTO(Long custContactId, String customerContactType, String customerContactValue,
			LocalDate effectiveDate, LocalDate startDate, LocalDate endDate) {
		super();
		this.custContactId = custContactId;
		this.customerContactType = customerContactType;
		this.customerContactValue = customerContactValue;
		this.effectiveDate = effectiveDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
}
