package com.example.demo.DTO;

import java.time.LocalDate;

public class custAddressDTO {

    private Long custAddressId;
    private String addressType;
    private String addressValue;
    private LocalDate effectiveDate;

    private Long custId;
    private Long customerClassificationId;

    public custAddressDTO() {}

    public Long getCustAddressId() {
        return custAddressId;
    }

    public void setCustAddressId(Long custAddressId) {
        this.custAddressId = custAddressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCustomerClassificationId() {
        return customerClassificationId;
    }

    public void setCustomerClassificationId(Long customerClassificationId) {
        this.customerClassificationId = customerClassificationId;
    }
}
