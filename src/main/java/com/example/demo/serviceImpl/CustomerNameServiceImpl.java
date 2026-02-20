package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custNameDTO;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerName;
import com.example.demo.repository.CustomerClassificationRepo;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CustomerNameRepo;
import com.example.demo.service.CustomerNameService;

@Service
public class CustomerNameServiceImpl implements CustomerNameService {

	@Autowired
	CustomerDetailRepo customerDetailRepo;
	
	@Autowired
	CustomerClassificationRepo classificationRepo;
	
	@Autowired
	CustomerNameRepo repo;
	
	public ResponseEntity<custNameDTO> addName(custNameDTO dto) {

	    CustomerDetail customer = customerDetailRepo.findById(dto.getCustId())
	            .orElseThrow(() -> new RuntimeException("Customer not found"));

	    CustomerClassificationType classification =
	            classificationRepo.findById(dto.getClassificationId())
	            .orElseThrow(() -> new RuntimeException("Classification not found"));

	    CustomerName entity = new CustomerName();
	    entity.setCustomerDetail_FK_Custname(customer);
	    entity.setClassificationType_FK_Custname(classification);
	    entity.setCustomerNameType(dto.getCustomerNameType());
	    entity.setCustomerNameValue(dto.getCustomerNameValue());
	    entity.setEffectiveDate(dto.getEffectiveDate());

	    CustomerName saved = repo.save(entity);

	    dto.setCustNameId(saved.getCustNameId());

	    return ResponseEntity.ok(dto);
	}
	
	public ResponseEntity<List<custNameDTO>> getAll() {

	    List<custNameDTO> list = repo.findAll()
	            .stream()
	            .filter(c->Objects.equals('D', c.getCrudval())!=true)
	            .map(n -> {
	                custNameDTO d = new custNameDTO();
	                d.setCustNameId(n.getCustNameId());
	                d.setCustomerNameType(n.getCustomerNameType());
	                d.setCustomerNameValue(n.getCustomerNameValue());
	                d.setEffectiveDate(n.getEffectiveDate());
	                d.setCustId(n.getCustomerDetail_FK_Custname().getCustomerId());
	                d.setClassificationId(
	                    n.getClassificationType_FK_Custname().getCustomerClassificationId());
	                return d;
	            })
	            .toList();

	    return ResponseEntity.ok(list);
	}
	
	public ResponseEntity<?> update(Long id, custNameDTO dto) {

	    CustomerName entity = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Name not found"));

	    if(Objects.equals('D', entity.getCrudval())==false) {
	    entity.setCustomerNameType(dto.getCustomerNameType());
	    entity.setCustomerNameValue(dto.getCustomerNameValue());
	    entity.setEffectiveDate(dto.getEffectiveDate());

	    entity.setCrudval('U');
	    repo.save(entity);

	    dto.setCustNameId(entity.getCustNameId());

	    return ResponseEntity.ok(dto);
	    }
	    else {
			return new ResponseEntity<>("Element already deleted",HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		
		CustomerName name=repo.findById(id).orElseThrow(()->new RuntimeException("ID not found"));
		
		if(Objects.equals('D', name.getCrudval())!=true) {
			name.setCrudval('D');
			repo.save(name);
			return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Element already deleted",HttpStatus.BAD_REQUEST);
		}
	}


}
