package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.custClassifDTO;

public interface CustomerClassificationService {

	public ResponseEntity<custClassifDTO> addclassification(custClassifDTO c);
	
	public ResponseEntity<List<custClassifDTO>> getAllClassifications();
	
	public ResponseEntity<?> updateClassification(Long id,custClassifDTO c);
	
	public ResponseEntity<String> deleteClassification(Long id);
}
