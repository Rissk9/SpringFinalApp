package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custAddressDTO;
import com.example.demo.entity.CustomerAddress;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.repository.CustomerClassificationRepo;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CutomerAddressRepo;
import com.example.demo.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Autowired
    private CutomerAddressRepo repo;

    @Autowired
    private CustomerDetailRepo customerRepo;

    @Autowired
    private CustomerClassificationRepo classificationRepo;

    // ---------------- POST ----------------
    @Override
    public ResponseEntity<custAddressDTO> add(custAddressDTO dto) {

        CustomerDetail customer = customerRepo.findById(dto.getCustId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerClassificationType classification = classificationRepo.findById(dto.getCustomerClassificationId())
                .orElseThrow(() -> new RuntimeException("Classification not found"));

        CustomerAddress entity = new CustomerAddress();
        entity.setCustomerAddressType(dto.getAddressType());
        entity.setCustomerAddressValue(dto.getAddressValue());
        entity.setEffectiveDate(dto.getEffectiveDate());

        entity.setCustomerDetail_FK_custAddress(customer);
        entity.setCustomerClassificationType_FK_custAddress(classification);

        CustomerAddress saved = repo.save(entity);

        dto.setCustAddressId(saved.getCustAddressId());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // ---------------- GET ----------------
    @Override
    public ResponseEntity<List<custAddressDTO>> getAll() {

        List<custAddressDTO> list = repo.findAll()
                .stream()
                .filter(c -> Objects.equals('D', c.getCrudval()) == false)
                .map(e -> {
                    custAddressDTO dto = new custAddressDTO();
                    dto.setCustAddressId(e.getCustAddressId());
                    dto.setAddressType(e.getCustomerAddressType());
                    dto.setAddressValue(e.getCustomerAddressValue());
                    dto.setEffectiveDate(e.getEffectiveDate());
                    dto.setCustId(
                            e.getCustomerDetail_FK_custAddress().getCustomerId());
                    dto.setCustomerClassificationId(
                            e.getCustomerClassificationType_FK_custAddress()
                                    .getCustomerClassificationId());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(list);
    }

    // ---------------- PUT ----------------
    @Override
    public ResponseEntity<?> update(Long id, custAddressDTO dto) {

        CustomerAddress existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (Objects.equals('D', existing.getCrudval()))
            return new ResponseEntity<String>("Element already deleted", HttpStatus.BAD_REQUEST);

        existing.setCustomerAddressType(dto.getAddressType());
        existing.setCustomerAddressValue(dto.getAddressValue());
        existing.setEffectiveDate(dto.getEffectiveDate());
        existing.setCrudval('U');

        repo.save(existing);

        dto.setCustAddressId(existing.getCustAddressId());

        return ResponseEntity.ok(dto);
    }

    // --------------DELETE----------------
    @Override
    public ResponseEntity<?> delete(Long id) {

        CustomerAddress address = repo.findById(id).orElseThrow(() -> new RuntimeException("Id not present"));

        if (!Objects.equals('D', address.getCrudval())) {
            address.setCrudval('D');
            repo.save(address);
            return new ResponseEntity<>(Map.of("message", "Deleted successfully"), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(Map.of("message", "Element already deleted"), HttpStatus.BAD_REQUEST);
        }
    }

}
