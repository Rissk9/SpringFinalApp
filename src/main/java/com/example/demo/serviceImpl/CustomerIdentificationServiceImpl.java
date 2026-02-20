package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private final CustomerClassificationController customerClassificationController;

    
	@Autowired
	CustomerIdentificationRepo repo;
	
	@Autowired
	CustomerDetailRepo custdetailrepo;


    CustomerIdentificationServiceImpl(CustomerClassificationController customerClassificationController) {
        this.customerClassificationController = customerClassificationController;
    }

	
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
			if(Objects.equals('D', n.getCrudval())==false) {
			custIndentifDTO temp=new custIndentifDTO();
			temp.setCustIdentificationItem(n.getCustIdentificationItem());
			temp.setCustIdentificationtype(n.getCustIdentificationtype());
			temp.setCustomerIdentifId(n.getCustomerIdentifId());
			temp.setEffectiveDate(n.getEffectiveDate());
			temp.setCustId(n.getCustomerDetail_FK().getCustomerId());
			
			IdentificationList.add(temp);
			}
		}
		
		return new ResponseEntity<List<custIndentifDTO>>(IdentificationList,HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateIdentification(Long id,custIndentifDTO dto){
		
		CustomerIdentification saved=repo.findById(id).orElseThrow(()-> new RuntimeException("Identification id not found "));
		if(saved.getCrudval()=='D')
			return new ResponseEntity<String>("Element has been deleted",HttpStatus.BAD_REQUEST);

		saved.setCustIdentificationItem(dto.getCustIdentificationItem());
		saved.setCustIdentificationtype(dto.getCustIdentificationtype());
		
		//never update the primary key
//		saved.setCustomerIdentifId(dto.getCustomerIdentifId());
		saved.setEffectiveDate(dto.getEffectiveDate());
		saved.setCrudval('U');
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

	@Override
	public ResponseEntity<?> deleteIdentification(Long id) {
		
		CustomerIdentification customerIdentification=repo.findById(id).orElseThrow(()->new RuntimeException("Id not found"));
		
		if(customerIdentification.getCrudval()=='D') 
			return new ResponseEntity<String>("Element has been deleted",HttpStatus.BAD_REQUEST);

		customerIdentification.setCrudval('D');
		repo.save(customerIdentification);
		
		return new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);

	}	
}
