package com.example.demo.DTO;

import java.time.LocalDate;

import com.example.demo.entity.CustomerDetail;

public class custProofOfIdDTO {

	private Long customerProofId;
	private String proofofIdType;
	private String proofofIdValue;
	private LocalDate effectivDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long custId;
	
	public custProofOfIdDTO() {
		super();
	}
		
	public custProofOfIdDTO(Long customerProofId, String proofofIdType, String proofofIdValue, LocalDate effectivDate,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.customerProofId = customerProofId;
		this.proofofIdType = proofofIdType;
		this.proofofIdValue = proofofIdValue;
		this.effectivDate = effectivDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getCustomerProofId() {
		return customerProofId;
	}
	public void setCustomerProofId(Long customerProofId) {
		this.customerProofId = customerProofId;
	}
	public String getProofofIdType() {
		return proofofIdType;
	}
	public void setProofofIdType(String proofofIdType) {
		this.proofofIdType = proofofIdType;
	}
	public String getProofofIdValue() {
		return proofofIdValue;
	}
	public void setProofofIdValue(String proofofIdValue) {
		this.proofofIdValue = proofofIdValue;
	}
	public LocalDate getEffectivDate() {
		return effectivDate;
	}
	public void setEffectivDate(LocalDate effectivDate) {
		this.effectivDate = effectivDate;
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
}
