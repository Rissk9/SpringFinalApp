package com.example.demo.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.custDetailDTO;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.service.CustomerDetailsService;

@RestController
@RequestMapping("/api/customers")
public class CustomerDetailController {

	@Autowired
	CustomerDetailsService custDetailService;
	
	@PostMapping
	public ResponseEntity<custDetailDTO> addCustomer(
	        @RequestBody custDetailDTO dto) {
	    return custDetailService.addCustomer(dto);
	}

	@GetMapping
	public ResponseEntity<List<custDetailDTO>> getAllCustomers() {
	    return custDetailService.getAllCustomers();
	}
	
	
	
	
	
	
	@PutMapping
	public ResponseEntity<custDetailDTO> updatecustomers(@RequestBody custDetailDTO custbody){
		return custDetailService.updateCustomer(custbody);
	}
}
