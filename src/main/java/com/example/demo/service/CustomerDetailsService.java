package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custDetailDTO;
import com.example.demo.entity.CustomerDetail;


public interface CustomerDetailsService {

	ResponseEntity<custDetailDTO> addCustomer(custDetailDTO custbody);

	ResponseEntity<List<custDetailDTO>> getAllCustomers();

	ResponseEntity<custDetailDTO> updateCustomer(custDetailDTO custbody);
	
	
}
