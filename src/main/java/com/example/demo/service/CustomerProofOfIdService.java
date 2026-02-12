package com.example.demo.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.DTO.custProofOfIdDTO;

public interface CustomerProofOfIdService {

    ResponseEntity<custProofOfIdDTO> add(custProofOfIdDTO dto);

    ResponseEntity<List<custProofOfIdDTO>> getAll();

    ResponseEntity<custProofOfIdDTO> update(Long id, custProofOfIdDTO dto);
}
