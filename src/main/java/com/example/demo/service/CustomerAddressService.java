package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.custAddressDTO;

public interface CustomerAddressService {

    ResponseEntity<custAddressDTO> add(custAddressDTO dto);

    ResponseEntity<List<custAddressDTO>> getAll();

    ResponseEntity<custAddressDTO> update(Long id, custAddressDTO dto);
}
