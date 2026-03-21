															
package com.example.demo.contollers;

import java.util.List;

import javax.crypto.interfaces.DHPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.custClassifDTO;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.serviceImpl.CustomerClassificationServiceImpl;

@RestController
@RequestMapping("/api/classification")
public class CustomerClassificationController {
	
	@Autowired
	CustomerClassificationServiceImpl classificationServiceImpl;

	@PostMapping
	public ResponseEntity<custClassifDTO> addCustomerClassification(@RequestBody custClassifDTO dto){
		
		return classificationServiceImpl.addclassification(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<custClassifDTO>> getAllClassifications() {
	    return classificationServiceImpl.getAllClassifications();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatCustomerCLassfication(@PathVariable Long id,@RequestBody custClassifDTO cType){
	
		return classificationServiceImpl.updateClassification(id,cType);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletingClassification(@PathVariable Long id) {
		return classificationServiceImpl.deleteClassification(id);
	} 

}
