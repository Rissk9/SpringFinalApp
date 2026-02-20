package com.example.demo.contollers;

import java.util.List;

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

import com.example.demo.DTO.custIndentifDTO;
import com.example.demo.serviceImpl.CustomerIdentificationServiceImpl;

@RestController
@RequestMapping("/api/identification")
public class CustomerIdentificationController {
	
	@Autowired
	CustomerIdentificationServiceImpl identificationService;
	
	@PostMapping
	public ResponseEntity<custIndentifDTO> postIdentification(@RequestBody custIndentifDTO dto){
		return identificationService.addIdentification(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<custIndentifDTO>> getAllEntityIdentification(){
		return identificationService.getIdentification();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putIdentification(@PathVariable Long id,@RequestBody custIndentifDTO dto){
		return identificationService.updateIdentification(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> putIdentification(@PathVariable Long id){
		return identificationService.deleteIdentification(id);
	}
}
