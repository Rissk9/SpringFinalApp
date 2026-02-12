package com.example.demo.DTO;

import java.time.LocalDate;

public class custIndentifDTO {

	private Long customerIdentifId;
	private String custIdentificationtype;
	private String custIdentificationItem;
	private Long custId;
	
	private LocalDate effectiveDate;
	
	
	
	
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public custIndentifDTO() {
		super();
	}
	public Long getCustomerIdentifId() {
		return customerIdentifId;
	}
	public void setCustomerIdentifId(Long customerIdentifId) {
		this.customerIdentifId = customerIdentifId;
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
	public custIndentifDTO(Long customerIdentifId, String custIdentificationtype, String custIdentificationItem,
			Long custId, LocalDate effectiveDate) {
		super();
		this.customerIdentifId = customerIdentifId;
		this.custIdentificationtype = custIdentificationtype;
		this.custIdentificationItem = custIdentificationItem;
		this.custId = custId;
		this.effectiveDate = effectiveDate;
	}
	
	
	
	
	

	
}
