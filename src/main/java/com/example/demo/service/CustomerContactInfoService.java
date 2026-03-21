package com.example.demo.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.DTO.custContactInfoDTO;

public interface CustomerContactInfoService {

    ResponseEntity<?> add(custContactInfoDTO dto);

    ResponseEntity<List<custContactInfoDTO>> getAll();

    ResponseEntity<?> update(Long id, custContactInfoDTO dto);
    
    ResponseEntity<?> delete(Long id);
    
}
