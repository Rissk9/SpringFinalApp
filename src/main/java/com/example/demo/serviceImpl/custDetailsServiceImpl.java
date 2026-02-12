package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custAddressDTO;
import com.example.demo.DTO.custContactInfoDTO;
import com.example.demo.DTO.custDetailDTO;
import com.example.demo.DTO.custNameDTO;
import com.example.demo.DTO.custProofOfIdDTO;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerIdentification;
import com.example.demo.entity.CustomerName;
import com.example.demo.entity.CustomerProofofId;
import com.example.demo.mapping.CustDetailMapper;
import com.example.demo.entity.CustomerAddress;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.entity.CustomerContactInformation;
import com.example.demo.repository.CustomerClassificationRepo;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CustomerProofofIdRepo;
import com.example.demo.service.CustomerDetailsService;

@Service
public class custDetailsServiceImpl implements CustomerDetailsService {

    private final CustomerProofofIdRepo customerProofofIdRepo;

	@Autowired
	private CustomerDetailRepo repo;
	
	@Autowired
	private CustomerClassificationRepo classificationRepo;


    custDetailsServiceImpl(CustomerProofofIdRepo customerProofofIdRepo) {
        this.customerProofofIdRepo = customerProofofIdRepo;
    }

	
	
//	@Override
//	public ResponseEntity<CustomerDetail> addCustomer(custDetailDTO custbody) {
//		CustomerDetail custDetailEntity=new CustomerDetail(custbody);
////		custDetailEntity.setCustomerId(custbody.getCustomerId());
////		custDetailEntity.setCustFullname(custbody.getCustFullname());
////		custDetailEntity.setCustGender(custbody.getCustGender());
////		custDetailEntity.setCustDate(custbody.getCustDate());
////		custDetailEntity.setCustPrefLanguage(custbody.getCustPrefLanguage());
////		custDetailEntity.setCustStatus(custbody.getCustStatus());
////		custDetailEntity.setCust_country(custbody.getCust_country());
////		custDetailEntity.setCustomerAddresses(custbody.getCustomerAddresses());
////		custDetailEntity.setCustomerNames(custbody.getCustomerNames());
////		custDetailEntity.setCustomerContactInformations(custbody.getCustomerContactInformations());
////		custDetailEntity.setCustomerProofofIds(custbody.getCustomerProofofIds());
////		custDetailEntity.setClassificationType(custDetailDTO.toEntity(custbody.getClassifDTO()));
////		custDetailEntity.setCustomerIdentification(custDetailDTO.toEntity(custbody.getCustIndentifDTO()));
//		
//		CustomerDetail saved=repo.save(custDetailEntity);
//		return new ResponseEntity<CustomerDetail>(saved,HttpStatus.ACCEPTED);
//		
//	}
	
	@Override
	public ResponseEntity<custDetailDTO> addCustomer(custDetailDTO dto) {


	    CustomerDetail customer = new CustomerDetail();
	    customer.setCustFullname(dto.getCustFullname());
	    customer.setCustGender(dto.getCustGender());
	    customer.setCustDate(dto.getCustDate());
	    customer.setCustPrefLanguage(dto.getCustPrefLanguage());
	    customer.setCustStatus(dto.getCustStatus());
	    customer.setCust_country(dto.getCustCountry());
	    
	    CustomerClassificationType classification =
	    	    classificationRepo.findById(dto.getClassificationId())
	    	        .orElseThrow(() ->
	    	            new RuntimeException("Invalid classification id"));
	    customer.setClassificationType(classification);
	    

	    if (dto.getIdentification() != null) {
	        CustomerIdentification ident = new CustomerIdentification();
	        ident.setCustIdentificationtype(
	            dto.getIdentification().getCustIdentificationtype());
	        ident.setCustIdentificationItem(
	            dto.getIdentification().getCustIdentificationItem());
	        ident.setEffectiveDate(
	            dto.getIdentification().getEffectiveDate());

	        ident.setCustomerDetail_FK(customer); // FK owner
	        customer.setCustomerIdentification(ident);
	    }
	    if(dto.getCustomerNames()!=null) {
	    	List<CustomerName> custnames=new ArrayList<>();
	    	for(custNameDTO n:dto.getCustomerNames()) {
	    		CustomerName tempCustomerName=new CustomerName(); 
	    		CustomerClassificationType classif=classificationRepo.findById(dto.getClassificationId())
	    				.orElseThrow(() -> new RuntimeException("Invalid id"));
	    		
	    		tempCustomerName.setClassificationType_FK_Custname(classif);
	    		
	    		tempCustomerName.setCustomerDetail_FK_Custname(customer);
//	    		tempCustomerName.setCustNameId(n.getCustNameId());
	    		tempCustomerName.setCustomerNameType(n.getCustomerNameType());
	    		tempCustomerName.setCustomerNameValue(n.getCustomerNameValue());
	    		tempCustomerName.setEffectiveDate(n.getEffectiveDate());
	    		
	    		custnames.add(tempCustomerName);
	    	}
	    customer.setCustomerNames(custnames);
	    }
	    
	    if(dto.getCustomerProofofIds()!=null) {
	    	List <CustomerProofofId> custproof=new ArrayList<>();
	    	for(custProofOfIdDTO n:dto.getCustomerProofofIds()) {
	    		CustomerProofofId ctemp=new CustomerProofofId();
//	    		ctemp.setCustomerProofId(n.getCustomerProofId());
	    		ctemp.setProofofIdType(n.getProofofIdType());
	    		ctemp.setProofofIdValue(n.getProofofIdValue());
	    		ctemp.setEffectivDate(n.getEffectivDate());
	    		ctemp.setStartDate(n.getStartDate());
	    		ctemp.setEndDate(n.getEndDate());
	    		ctemp.setCustomerDetail_fk_proofofId(customer);
	    		
	    		custproof.add(ctemp);
	    	}
	    	customer.setCustomerProofofIds(custproof);
	    }
	    if(dto.getCustomerContactInformations()!=null) {
	    	List <CustomerContactInformation> custcontact=new ArrayList<>();
	    	for(custContactInfoDTO n:dto.getCustomerContactInformations()) {
	    		CustomerContactInformation temp=new CustomerContactInformation();
//	    		temp.setCustContactId(n.getCustContactId());
	    		temp.setCustomerContactType(n.getCustomerContactType());
	    		temp.setCustomerContactValue(n.getCustomerContactValue());
	    		temp.setCustomerDetail(customer);
	    		temp.setEffectiveDate(n.getEffectiveDate());
	    		temp.setEndDate(n.getEndDate());
	    		temp.setStartDate(n.getStartDate());
	    		custcontact.add(temp);
	    	}
	    	customer.setCustomerContactInformations(custcontact);
	    }
	    if(dto.getCustomerAddresses()!=null) {
	    	List<CustomerAddress> addresses=new ArrayList<>();
	    	
	    	for(custAddressDTO n:dto.getCustomerAddresses()) {
	    		CustomerAddress temp=new CustomerAddress();
	    		temp.setCustomerAddressType(n.getAddressType());
	    		temp.setCustomerAddressValue(n.getAddressValue());
	    		temp.setEffectiveDate(n.getEffectiveDate());
	    		CustomerClassificationType c=classificationRepo.findById(dto.getClassificationId())
	    				.orElseThrow(()-> new RuntimeException("Classification id not found"));
	    		
	    		temp.setCustomerClassificationType_FK_custAddress(c);
	    		temp.setCustomerDetail_FK_custAddress(customer);
	    		
	    		addresses.add(temp);
	    	}
	    	customer.setCustomerAddresses(addresses);
	    }
	    
	    

	    CustomerDetail saved = repo.save(customer);
	    custDetailDTO response=CustDetailMapper.toDTO(saved);
	    
	    return new ResponseEntity<>(response, HttpStatus.CREATED);  	    
	}

	@Override
	public ResponseEntity<List<custDetailDTO>> getAllCustomers() {
		 List<CustomerDetail> entities = repo.findAll();
		    List<custDetailDTO> customers = new ArrayList<>();

		    for (CustomerDetail entity : entities) {
		        custDetailDTO dto = CustDetailMapper.toDTO(entity);
		        customers.add(dto);
		    }

		    return new ResponseEntity<>(customers, HttpStatus.OK);
	}

//	@Override
	public ResponseEntity<custDetailDTO> updateCustomer(custDetailDTO dto) {
		
		CustomerDetail existing=repo.findById(dto.getCustId()).orElseThrow(()->new RuntimeException("Id not found in the database"));
		
		CustomerClassificationType classification=classificationRepo.findById(dto.getClassificationId()).orElseThrow(()->new RuntimeException("Classification ID not present in the database"));
		existing.setClassificationType(classification);
		existing.setCust_country(dto.getCustCountry());
		existing.setCustDate(dto.getCustDate());
		existing.setCustFullname(dto.getCustFullname());
		existing.setCustGender(dto.getCustGender());
		existing.setCustPrefLanguage(dto.getCustPrefLanguage());
		
	    
		if(dto.getCustomerAddresses()!=null) {
	    	List<CustomerAddress> addresses=new ArrayList<>();
	    	
	    	for(custAddressDTO n:dto.getCustomerAddresses()) {
	    		CustomerAddress temp=new CustomerAddress();
	    		temp.setCustomerAddressType(n.getAddressType());
	    		temp.setCustomerAddressValue(n.getAddressValue());
	    		temp.setEffectiveDate(n.getEffectiveDate());
	    		CustomerClassificationType c=classificationRepo.findById(dto.getClassificationId())
	    				.orElseThrow(()-> new RuntimeException("Classification id not found"));
	    		
	    		temp.setCustomerClassificationType_FK_custAddress(c);
	    		temp.setCustomerDetail_FK_custAddress(existing);
	    		
	    		addresses.add(temp);
	    	}
	    	existing.setCustomerAddresses(addresses);	
	    }
		if(dto.getCustomerContactInformations()!=null) {
	    	List <CustomerContactInformation> custcontact=new ArrayList<>();
	    	for(custContactInfoDTO n:dto.getCustomerContactInformations()) {
	    		CustomerContactInformation temp=new CustomerContactInformation();
//	    		temp.setCustContactId(n.getCustContactId());
	    		temp.setCustomerContactType(n.getCustomerContactType());
	    		temp.setCustomerContactValue(n.getCustomerContactValue());
	    		temp.setCustomerDetail(existing);
	    		temp.setEffectiveDate(n.getEffectiveDate());
	    		temp.setEndDate(n.getEndDate());
	    		temp.setStartDate(n.getStartDate());
	    		custcontact.add(temp);
	    	}
	    	existing.setCustomerContactInformations(custcontact);
	    }
		if(dto.getCustomerProofofIds()!=null) {
	    	List <CustomerProofofId> custproof=new ArrayList<>();
	    	for(custProofOfIdDTO n:dto.getCustomerProofofIds()) {
	    		CustomerProofofId ctemp=new CustomerProofofId();
//	    		ctemp.setCustomerProofId(n.getCustomerProofId());
	    		ctemp.setProofofIdType(n.getProofofIdType());
	    		ctemp.setProofofIdValue(n.getProofofIdValue());
	    		ctemp.setEffectivDate(n.getEffectivDate());
	    		ctemp.setStartDate(n.getStartDate());
	    		ctemp.setEndDate(n.getEndDate());
	    		ctemp.setCustomerDetail_fk_proofofId(existing);
	    		
	    		custproof.add(ctemp);
	    	}
	    	existing.setCustomerProofofIds(custproof);
	    }
		 if (dto.getIdentification() != null) {
//		        CustomerIdentification ident = new CustomerIdentification();
//		        ident.setCustIdentificationtype(
//		            dto.getIdentification().getCustIdentificationtype());
//		        ident.setCustIdentificationItem(
//		            dto.getIdentification().getCustIdentificationItem());
//		        ident.setEffectiveDate(
//		            dto.getIdentification().getEffectiveDate());
//
//		        ident.setCustomerDetail_FK(existing); // FK owner
//		        existing.setCustomerIdentification(ident);
			 
			 CustomerIdentification ident = existing.getCustomerIdentification();

			    // if already present → UPDATE
			    if (ident == null) {
			        ident = new CustomerIdentification();
			        ident.setCustomerDetail_FK(existing);
			    }

			    ident.setCustIdentificationtype(dto.getIdentification().getCustIdentificationtype());
			    ident.setCustIdentificationItem(dto.getIdentification().getCustIdentificationItem());
			    ident.setEffectiveDate(dto.getIdentification().getEffectiveDate());

			    existing.setCustomerIdentification(ident);
		    }
		 
		 if(dto.getCustomerNames()!=null) {
		    	List<CustomerName> custnames=new ArrayList<>();
		    	for(custNameDTO n:dto.getCustomerNames()) {
		    		CustomerName tempCustomerName=new CustomerName(); 
		    		CustomerClassificationType classif=classification;
		    		
		    		tempCustomerName.setClassificationType_FK_Custname(classif);
		    		
		    		tempCustomerName.setCustomerDetail_FK_Custname(existing);
//		    		tempCustomerName.setCustNameId(n.getCustNameId());
		    		tempCustomerName.setCustomerNameType(n.getCustomerNameType());
		    		tempCustomerName.setCustomerNameValue(n.getCustomerNameValue());
		    		tempCustomerName.setEffectiveDate(n.getEffectiveDate());
		    		
		    		custnames.add(tempCustomerName);
		    	}
		    existing.setCustomerNames(custnames);
		    }
		 CustomerDetail ctemp=repo.save(existing);
		 return new ResponseEntity<custDetailDTO>(CustDetailMapper.toDTO(ctemp),HttpStatus.OK);
	}
}
