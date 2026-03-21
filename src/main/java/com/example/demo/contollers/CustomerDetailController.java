package com.example.demo.contollers;

import java.util.List;
import com.example.demo.serviceImpl.custDetailsServiceImpl;
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

import com.example.demo.DTO.custDetailDTO;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.service.CustomerDetailsService;

@RestController
@RequestMapping("/api/customers")
public class CustomerDetailController {

//    private final serviceImpl.custDetailsServiceImpl custDetailsServiceImpl;

	@Autowired
	CustomerDetailsService custDetailService;
	
	@PostMapping
	public ResponseEntity<?> addCustomer(
	        @RequestBody custDetailDTO dto) {
	    return custDetailService.addCustomer(dto);
	}

	@GetMapping
	public ResponseEntity<List<custDetailDTO>> getAllCustomers() {
	    return custDetailService.getAllCustomers();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteByid(@PathVariable Long id){
		return custDetailService.deleteCustomer(id);
	}
	
	
	
	@PutMapping
	public ResponseEntity<?> updatecustomers(@RequestBody custDetailDTO custbody){
		return custDetailService.updateCustomer(custbody);
	}
}
