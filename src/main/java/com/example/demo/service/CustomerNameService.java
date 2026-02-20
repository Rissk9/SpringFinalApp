package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.custNameDTO;

public interface CustomerNameService {

    ResponseEntity<custNameDTO> addName(custNameDTO dto);

    ResponseEntity<List<custNameDTO>> getAll();

    ResponseEntity<?> update(Long id, custNameDTO dto);

	ResponseEntity<?> delete(Long id);
}
