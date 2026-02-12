package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custProofOfIdDTO;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerProofofId;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CustomerProofofIdRepo;
import com.example.demo.service.CustomerProofOfIdService;

@Service
public class CustomerProofOfIdServiceImpl implements CustomerProofOfIdService {

    @Autowired
    private CustomerProofofIdRepo repo;

    @Autowired
    private CustomerDetailRepo customerRepo;

    // ---------------- POST ----------------
    @Override
    public ResponseEntity<custProofOfIdDTO> add(custProofOfIdDTO dto) {

        CustomerDetail customer = customerRepo.findById(dto.getCustId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerProofofId entity = new CustomerProofofId();
        entity.setProofofIdType(dto.getProofofIdType());
        entity.setProofofIdValue(dto.getProofofIdValue());
        entity.setEffectivDate(dto.getEffectivDate());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        entity.setCustomerDetail_fk_proofofId(customer);

        CustomerProofofId saved = repo.save(entity);

        dto.setCustomerProofId(saved.getCustomerProofId());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // ---------------- GET ----------------
    @Override
    public ResponseEntity<List<custProofOfIdDTO>> getAll() {

        List<custProofOfIdDTO> list =
                repo.findAll()
                    .stream()
                    .map(e -> {
                        custProofOfIdDTO dto = new custProofOfIdDTO();
                        dto.setCustomerProofId(e.getCustomerProofId());
                        dto.setProofofIdType(e.getProofofIdType());
                        dto.setProofofIdValue(e.getProofofIdValue());
                        dto.setEffectivDate(e.getEffectivDate());
                        dto.setStartDate(e.getStartDate());
                        dto.setEndDate(e.getEndDate());
                        dto.setCustId(
                            e.getCustomerDetail_fk_proofofId().getCustomerId()
                        );
                        return dto;
                    })
                    .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    // ---------------- PUT ----------------
    @Override
    public ResponseEntity<custProofOfIdDTO> update(Long id, custProofOfIdDTO dto) {

        CustomerProofofId existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proof id not found"));

        existing.setProofofIdType(dto.getProofofIdType());
        existing.setProofofIdValue(dto.getProofofIdValue());
        existing.setEffectivDate(dto.getEffectivDate());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());

        repo.save(existing);

        dto.setCustomerProofId(existing.getCustomerProofId());

        return ResponseEntity.ok(dto);
    }
}
