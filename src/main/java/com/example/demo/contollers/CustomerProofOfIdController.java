package com.example.demo.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.custProofOfIdDTO;
import com.example.demo.service.CustomerProofOfIdService;

@RestController
@RequestMapping("/api/proof")
public class CustomerProofOfIdController {

    @Autowired
    private CustomerProofOfIdService service;

    // POST
    @PostMapping
    public ResponseEntity<custProofOfIdDTO> add(@RequestBody custProofOfIdDTO dto) {
        return service.add(dto);
    }

    // GET
    @GetMapping
    public ResponseEntity<List<custProofOfIdDTO>> getAll() {
        return service.getAll();
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<custProofOfIdDTO> update(
            @PathVariable Long id,
            @RequestBody custProofOfIdDTO dto) {
        return service.update(id, dto);
    }
}
