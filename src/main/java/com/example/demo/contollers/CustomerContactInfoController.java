package com.example.demo.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.custContactInfoDTO;
import com.example.demo.service.CustomerContactInfoService;

@RestController
@RequestMapping("/api/contactinfo")
public class CustomerContactInfoController {

    @Autowired
    private CustomerContactInfoService service;

    @PostMapping
    public ResponseEntity<custContactInfoDTO> add(@RequestBody custContactInfoDTO dto) {
        return service.add(dto);
    }

    @GetMapping
    public ResponseEntity<List<custContactInfoDTO>> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody custContactInfoDTO dto) {
        return service.update(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {
        return service.delete(id);
    }
}
