package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custIndentifDTO;
import com.example.demo.contollers.CustomerClassificationController;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerIdentification;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CustomerIdentificationRepo;
import com.example.demo.service.CustomerIdentificationService;

@Service
public class CustomerIdentificationServiceImpl implements CustomerIdentificationService{

    
	@Autowired
	CustomerIdentificationRepo repo;
	
	@Autowired
	CustomerDetailRepo custdetailrepo;

	
	public ResponseEntity<custIndentifDTO> addIdentification(custIndentifDTO dto) {
		
		CustomerIdentification customerIdentification=new CustomerIdentification();
//		customerIdentification.setCustomerIdentifId(dto.getCustomerIdentifId());
		customerIdentification.setCustIdentificationItem(dto.getCustIdentificationItem());
		customerIdentification.setCustIdentificationtype(dto.getCustIdentificationtype());
		customerIdentification.setEffectiveDate(dto.getEffectiveDate());
		
		CustomerDetail cdetail=custdetailrepo.findById(dto.getCustId()).orElseThrow(()-> new RuntimeException("Customer detail id not found"));
		customerIdentification.setCustomerDetail_FK(cdetail);
		
		CustomerIdentification c=repo.save(customerIdentification);
		custIndentifDTO d=new custIndentifDTO();
		
		d.setCustomerIdentifId(c.getCustomerIdentifId());
		d.setCustIdentificationItem(c.getCustIdentificationItem());
		d.setCustIdentificationtype(c.getCustIdentificationtype());
		d.setEffectiveDate(c.getEffectiveDate());
		
		return new ResponseEntity<custIndentifDTO>(d,HttpStatus.OK) ;
		
	}
	
	public ResponseEntity<List<custIndentifDTO>> getIdentification(){
		
		List <custIndentifDTO> IdentificationList=new ArrayList<>();
		for(CustomerIdentification n:repo.findAll()) {
			custIndentifDTO temp=new custIndentifDTO();
			temp.setCustIdentificationItem(n.getCustIdentificationItem());
			temp.setCustIdentificationtype(n.getCustIdentificationtype());
			temp.setCustomerIdentifId(n.getCustomerIdentifId());
			temp.setEffectiveDate(n.getEffectiveDate());
			temp.setCustId(n.getCustomerDetail_FK().getCustomerId());
			
			IdentificationList.add(temp);
		}
		
		return new ResponseEntity<List<custIndentifDTO>>(IdentificationList,HttpStatus.OK);
	}
	
	public ResponseEntity<custIndentifDTO> updateIdentification(Long id,custIndentifDTO dto){
		
		CustomerIdentification saved=repo.findById(id).orElseThrow(()-> new RuntimeException("Identification id not found "));
		
		saved.setCustIdentificationItem(dto.getCustIdentificationItem());
		saved.setCustIdentificationtype(dto.getCustIdentificationtype());
		
		//never update the primary key
//		saved.setCustomerIdentifId(dto.getCustomerIdentifId());
		saved.setEffectiveDate(dto.getEffectiveDate());
		
		repo.save(saved);
		
		//back to dto
		
		custIndentifDTO result=new custIndentifDTO();
		result.setCustId(saved.getCustomerDetail_FK().getCustomerId());
		result.setCustIdentificationItem(saved.getCustIdentificationItem());
		result.setCustIdentificationtype(saved.getCustIdentificationtype());
		result.setCustomerIdentifId(saved.getCustomerIdentifId());
		result.setEffectiveDate(saved.getEffectiveDate());
		
		return new ResponseEntity<custIndentifDTO>(result,HttpStatus.ACCEPTED) ;
				
	}	
}
