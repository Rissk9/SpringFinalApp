package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.CustomerAddress;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.entity.CustomerContactInformation;
import com.example.demo.entity.CustomerIdentification;
import com.example.demo.entity.CustomerName;
import com.example.demo.entity.CustomerProofofId;


public class custDetailDTO {

	private Long custId;
	private String custFullname;
    private String custGender;
    private LocalDate custDate;
    private String custPrefLanguage;
    private String custStatus;
    private String custCountry;
    private custIndentifDTO identification;
    
   

    
    private List<custNameDTO> customerNames;
    private List<custProofOfIdDTO> customerProofofIds;
    private List<custContactInfoDTO> customerContactInformations;
    private List<custAddressDTO> customerAddresses;
    

    private Long classificationId;
    
    

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public List<custNameDTO> getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(List<custNameDTO> customerNames) {
		this.customerNames = customerNames;
	}

	public List<custProofOfIdDTO> getCustomerProofofIds() {
		return customerProofofIds;
	}

	public void setCustomerProofofIds(List<custProofOfIdDTO> customerProofofIds) {
		this.customerProofofIds = customerProofofIds;
	}

	public List<custContactInfoDTO> getCustomerContactInformations() {
		return customerContactInformations;
	}

	public void setCustomerContactInformations(List<custContactInfoDTO> customerContactInformations) {
		this.customerContactInformations = customerContactInformations;
	}

	public List<custAddressDTO> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(List<custAddressDTO> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public custIndentifDTO getIdentification() {
		return identification;
	}

	public void setIdentification(custIndentifDTO identification) {
		this.identification = identification;
	}

	// reference entity → ID ONLY

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

	public String getCustCountry() {
		return custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public Long getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Long classificationId) {
		this.classificationId = classificationId;
	}
    
    
//	private Long customerId;
//	private String custFullname;
//	private String custGender;
//	private LocalDate custDate;
//	private String custPrefLanguage;
//	private String custStatus;
//	private String cust_country;
//	
//	private custClassifDTO classifDTO;
//	private custIndentifDTO custIndentifDTO;
//	
//	private List<CustomerName> customerNames;
//	private List<CustomerProofofId> customerProofofIds;
//	private List<CustomerContactInformation> customerContactInformations;
//	private List<CustomerAddress> customerAddresses;
//	
//	
//	
//	public custDetailDTO() {
//		super();
//	}
//	
//	
//
//	public custDetailDTO(Long customerId, String custFullname, String custGender, LocalDate custDate,
//			String custPrefLanguage, String custStatus, String cust_country, custClassifDTO classifDTO,
//			com.example.demo.DTO.custIndentifDTO custIndentifDTO, List<CustomerName> customerNames,
//			List<CustomerProofofId> customerProofofIds, List<CustomerContactInformation> customerContactInformations,
//			List<CustomerAddress> customerAddresses) {
//		super();
//		this.customerId = customerId;
//		this.custFullname = custFullname;
//		this.custGender = custGender;
//		this.custDate = custDate;
//		this.custPrefLanguage = custPrefLanguage;
//		this.custStatus = custStatus;
//		this.cust_country = cust_country;
//		this.classifDTO = classifDTO;
//		this.custIndentifDTO = custIndentifDTO;
//		this.customerNames = customerNames;
//		this.customerProofofIds = customerProofofIds;
//		this.customerContactInformations = customerContactInformations;
//		this.customerAddresses = customerAddresses;
//	}
//
//	public static CustomerClassificationType toEntity(custClassifDTO c) {
//		CustomerClassificationType ClassifEntity=new CustomerClassificationType(c);
//		return ClassifEntity;
//		
//	}
//
//	
//	public custClassifDTO getClassifDTO() {
//		return classifDTO;
//	}
//	public void setClassifDTO(custClassifDTO classifDTO) {
//		this.classifDTO = classifDTO;
//	}
//	public custIndentifDTO getCustIndentifDTO() {
//		return custIndentifDTO;
//	}
//	public void setCustIndentifDTO(custIndentifDTO custIndentifDTO) {
//		this.custIndentifDTO = custIndentifDTO;
//	}
//	public List<CustomerName> getCustomerNames() {
//		return customerNames;
//	}
//
//	public void setCustomerNames(List<CustomerName> customerNames) {
//		this.customerNames = customerNames;
//	}
//
//	public List<CustomerProofofId> getCustomerProofofIds() {
//		return customerProofofIds;
//	}
//
//	public void setCustomerProofofIds(List<CustomerProofofId> customerProofofIds) {
//		this.customerProofofIds = customerProofofIds;
//	}
//
//	public List<CustomerContactInformation> getCustomerContactInformations() {
//		return customerContactInformations;
//	}
//
//	public void setCustomerContactInformations(List<CustomerContactInformation> customerContactInformations) {
//		this.customerContactInformations = customerContactInformations;
//	}
//
//	public List<CustomerAddress> getCustomerAddresses() {
//		return customerAddresses;
//	}
//
//	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
//		this.customerAddresses = customerAddresses;
//	}
//
//	public Long getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(Long customerId) {
//		this.customerId = customerId;
//	}
//	public String getCustFullname() {
//		return custFullname;
//	}
//	public void setCustFullname(String custFullname) {
//		this.custFullname = custFullname;
//	}
//	public String getCustGender() {
//		return custGender;
//	}
//	public void setCustGender(String custGender) {
//		this.custGender = custGender;
//	}
//	public LocalDate getCustDate() {
//		return custDate;
//	}
//	public void setCustDate(LocalDate custDate) {
//		this.custDate = custDate;
//	}
//	public String getCustPrefLanguage() {
//		return custPrefLanguage;
//	}
//	public void setCustPrefLanguage(String custPrefLanguage) {
//		this.custPrefLanguage = custPrefLanguage;
//	}
//	public String getCustStatus() {
//		return custStatus;
//	}
//	public void setCustStatus(String custStatus) {
//		this.custStatus = custStatus;
//	}
//	public String getCust_country() {
//		return cust_country;
//	}
//	public void setCust_country(String cust_country) {
//		this.cust_country = cust_country;
//	}
//
//
//
//	public static CustomerIdentification toEntity(custIndentifDTO c) {
//		CustomerIdentification customerIdentification=new CustomerIdentification(c);
//		return customerIdentification;
//	}
		
}
