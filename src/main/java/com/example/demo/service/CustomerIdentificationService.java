package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.custIndentifDTO;

public interface CustomerIdentificationService {

	ResponseEntity<custIndentifDTO>  addIdentification(custIndentifDTO dto);

	ResponseEntity<List<custIndentifDTO>> getIdentification();

	ResponseEntity<?> updateIdentification(Long id,custIndentifDTO dto);

	ResponseEntity<?> deleteIdentification(Long id);

}
