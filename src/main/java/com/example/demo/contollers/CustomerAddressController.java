package com.example.demo.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.custAddressDTO;
import com.example.demo.service.CustomerAddressService;

@RestController
@RequestMapping("/api/address")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService service;

    @PostMapping
    public ResponseEntity<custAddressDTO> add(@RequestBody custAddressDTO dto) {
        return service.add(dto);
    }

    @GetMapping
    public ResponseEntity<List<custAddressDTO>> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody custAddressDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {
        return service.delete(id);
    }
}
