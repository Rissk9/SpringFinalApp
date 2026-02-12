package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custContactInfoDTO;
import com.example.demo.entity.CustomerContactInformation;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.repository.CustomerContactInformationRepo;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.service.CustomerContactInfoService;

@Service
public class CustomerContactInfoServiceImpl implements CustomerContactInfoService {

    @Autowired
    private CustomerContactInformationRepo repo;

    @Autowired
    private CustomerDetailRepo customerRepo;

    // ---------------- POST ----------------
    @Override
    public ResponseEntity<custContactInfoDTO> add(custContactInfoDTO dto) {

        CustomerDetail customer = customerRepo.findById(dto.getCustId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerContactInformation entity = new CustomerContactInformation();
        entity.setCustomerContactType(dto.getCustomerContactType());
        entity.setCustomerContactValue(dto.getCustomerContactValue());
        entity.setEffectiveDate(dto.getEffectiveDate());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        entity.setCustomerDetail(customer);

        CustomerContactInformation saved = repo.save(entity);

        dto.setCustContactId(saved.getCustContactId());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // ---------------- GET ----------------
    @Override
    public ResponseEntity<List<custContactInfoDTO>> getAll() {

        List<custContactInfoDTO> list =
                repo.findAll()
                    .stream()
                    .map(e -> {
                        custContactInfoDTO dto = new custContactInfoDTO();
                        dto.setCustContactId(e.getCustContactId());
                        dto.setCustomerContactType(e.getCustomerContactType());
                        dto.setCustomerContactValue(e.getCustomerContactValue());
                        dto.setEffectiveDate(e.getEffectiveDate());
                        dto.setStartDate(e.getStartDate());
                        dto.setEndDate(e.getEndDate());
                        dto.setCustId(
                            e.getCustomerDetail().getCustomerId()
                        );
                        return dto;
                    })
                    .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    // ---------------- PUT ----------------
    @Override
    public ResponseEntity<custContactInfoDTO> update(Long id, custContactInfoDTO dto) {

        CustomerContactInformation existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact id not found"));

        existing.setCustomerContactType(dto.getCustomerContactType());
        existing.setCustomerContactValue(dto.getCustomerContactValue());
        existing.setEffectiveDate(dto.getEffectiveDate());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());

        repo.save(existing);

        dto.setCustContactId(existing.getCustContactId());

        return ResponseEntity.ok(dto);
    }
}
