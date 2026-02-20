package com.example.demo.contollers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.custNameDTO;
import com.example.demo.service.CustomerNameService;

@RestController
@RequestMapping("/api/customernames")
public class CustomerNameController {

    @Autowired
    private CustomerNameService service;

    @PostMapping
    public ResponseEntity<custNameDTO> add(@RequestBody custNameDTO dto) {
        return service.addName(dto);
    }

    @GetMapping
    public ResponseEntity<List<custNameDTO>> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody custNameDTO dto) {
        return service.update(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {
        return service.delete(id);
    }
}
