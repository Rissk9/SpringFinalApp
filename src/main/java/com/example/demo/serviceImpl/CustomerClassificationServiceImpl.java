package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custAddressDTO;
import com.example.demo.DTO.custClassifDTO;
import com.example.demo.DTO.custContactInfoDTO;
import com.example.demo.DTO.custDetailDTO;
import com.example.demo.DTO.custNameDTO;
import com.example.demo.DTO.custProofOfIdDTO;
import com.example.demo.entity.CustomerAddress;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.entity.CustomerContactInformation;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerIdentification;
import com.example.demo.entity.CustomerName;
import com.example.demo.entity.CustomerProofofId;
import com.example.demo.mapping.CustDetailMapper;
import com.example.demo.repository.CustomerClassificationRepo;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CustomerIdentificationRepo;
import com.example.demo.service.CustomerClassificationService;

@Service
public class CustomerClassificationServiceImpl implements CustomerClassificationService {

	@Autowired
	CustomerDetailRepo customerDetailRepo;

	@Autowired
	CustomerClassificationRepo classificationRepo;

	@Autowired
	CustomerIdentificationRepo identificationrepo;

	// public ResponseEntity<custClassifDTO> addclassification(custClassifDTO dto) {
	// CustomerClassificationType cType=new CustomerClassificationType();
	//
	//
	//
	//
	// cType.setCustomerAddresses(dto.getCustomerAddresses());
	// cType.setCustomerClassificationType(dto.getCustomerClassificationType());
	// cType.setCustomerClassificationValue(dto.getCustomerClassificationValue());
	// cType.setEffectiveDate(dto.getEffectiveDate());
	// cType.setCustomerClassificationValue(dto.getCustomerClassificationValue());
	//
	// List<CustomerDetail> customerDetailEntities=new ArrayList<>();
	//
	// //we need to map all the objetcts that are there in the list from dto to
	// object
	//
	// for(custDetailDTO t:dto.getCustomerDetails()) {
	// CustomerDetail temp;
	// CustomerClassificationType
	// classificationType=classificationRepo.findById(t.getClassificationId()).orElseThrow(()->
	// new RuntimeException("ID not found"));
	//
	// CustomerIdentification cIdentification=new
	// CustomerIdentification(t.getIdentification().getCustomerIdentifId(),
	// t.getIdentification().getCustIdentificationtype(), temp,
	// t.getIdentification().getCustIdentificationItem(),
	// t.getIdentification().getEffectiveDate());
	//
	// List<CustomerName> allcustomerNames=new ArrayList<>();
	// for(custNameDTO nameDTO:t.getCustomerNames()) {
	// CustomerName tempCustomerName=new CustomerName(nameDTO.getCustNameId(), temp,
	// classificationType, nameDTO.getCustomerNameType(),
	// nameDTO.getCustomerNameValue(), nameDTO.getEffectiveDate());
	// allcustomerNames.add(tempCustomerName);
	// }
	//
	// List<CustomerProofofId> allProofofIds=new ArrayList<>();
	// for(custProofOfIdDTO proofOfIdDTO:t.getCustomerProofofIds()) {
	// CustomerProofofId proofs=new
	// CustomerProofofId(proofOfIdDTO.getCustomerProofId(), temp,
	// proofOfIdDTO.getProofofIdType(), proofOfIdDTO.getProofofIdValue(),
	// proofOfIdDTO.getEffectivDate(), proofOfIdDTO.getStartDate(),
	// proofOfIdDTO.getEndDate());
	// allProofofIds.add(proofs);
	// }
	//
	// List<CustomerContactInformation> allContactInformations=new ArrayList<>();
	// for(custContactInfoDTO infoDTO:t.getCustomerContactInformations()) {
	// CustomerContactInformation cInformation=new
	// CustomerContactInformation(infoDTO.getCustContactId(), temp,
	// infoDTO.getCustomerContactType(), infoDTO.getCustomerContactValue(),
	// infoDTO.getEffectiveDate(), infoDTO.getStartDate(), infoDTO.getEndDate());
	// allContactInformations.add(cInformation);
	// }
	//
	// List<CustomerAddress> allAddresses=new ArrayList<>();
	// for(custAddressDTO addressDTO:t.getCustomerAddresses()) {
	// CustomerAddress cAddress=new CustomerAddress(addressDTO.get, temp,
	// classificationType,
	// addressDTO.getCustomerAddressType(),addressDTO.getCustomerAddressValue(),addressDTO.getEffectiveDate());
	// allAddresses.add(cAddress);
	// }
	//
	//
	//
	// temp=new CustomerDetail(t.getCustId(), t.getCustFullname(),
	// t.getCustGender(), t.getCustDate(), t.getCustPrefLanguage(),
	// t.getCustStatus(), t.getCustCountry(),classificationType
	// ,cIdentification,allcustomerNames, allProofofIds, allContactInformations,
	// allAddresses);
	// customerDetailEntities.add(temp);
	// }
	// cType.setCustomerDetails(customerDetailEntities);
	// CustomerDetail cDetail=new CustomerDetail();
	//// cType.setCustomerDetails(null);
	// }

	@Override
	public ResponseEntity<custClassifDTO> addclassification(custClassifDTO dto) {

		CustomerClassificationType entity = new CustomerClassificationType();
		entity.setCustomerClassificationType(dto.getCustomerClassificationType());
		entity.setCustomerClassificationValue(dto.getCustomerClassificationValue());
		entity.setEffectiveDate(dto.getEffectiveDate());

		CustomerClassificationType saved = classificationRepo.save(entity);

		custClassifDTO response = new custClassifDTO();
		response.setCustomerClassificationId(saved.getCustomerClassificationId());
		response.setCustomerClassificationType(saved.getCustomerClassificationType());
		response.setCustomerClassificationValue(saved.getCustomerClassificationValue());
		response.setEffectiveDate(saved.getEffectiveDate());

		return ResponseEntity.ok(response);
	}

	// @Override
	// public ResponseEntity<List<custClassifDTO>> getAllClassifications() {
	//
	// List<custClassifDTO> list =
	// classificationRepo.findAll()
	// .stream()
	// .map(this::convertToDTO)
	// .toList();
	//
	//
	// return ResponseEntity.ok(list);
	// }

	@Override
	public ResponseEntity<List<custClassifDTO>> getAllClassifications() {

		List<custClassifDTO> list = classificationRepo.findAll()
				.stream()
				.filter(c -> Objects.equals('D', c.getCrudval()) == false)
				.map(classif -> {

					custClassifDTO dto = new custClassifDTO();
					dto.setCustomerClassificationId(classif.getCustomerClassificationId());
					dto.setCustomerClassificationType(classif.getCustomerClassificationType());
					dto.setCustomerClassificationValue(classif.getCustomerClassificationValue());
					dto.setEffectiveDate(classif.getEffectiveDate());

					// fetch customers using repo
					List<custDetailDTO> customers = customerDetailRepo
							.findByClassificationType_CustomerClassificationId(
									classif.getCustomerClassificationId())
							.stream()
							.map(CustDetailMapper::toDTO)
							.toList();

					dto.setCustomerDetails(customers);

					return dto;
				})
				.toList();

		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<?> updateClassification(Long id, custClassifDTO dto) {

		CustomerClassificationType existing = classificationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Classification not found"));

		if (Objects.equals('D', existing.getCrudval()))
			return new ResponseEntity<String>("Element already deleted", HttpStatus.BAD_REQUEST);

		// update only own fields
		existing.setCustomerClassificationType(dto.getCustomerClassificationType());
		existing.setCustomerClassificationValue(dto.getCustomerClassificationValue());
		existing.setEffectiveDate(dto.getEffectiveDate());
		existing.setCrudval('U');

		CustomerClassificationType saved = classificationRepo.save(existing);

		// convert back to dto
		custClassifDTO response = new custClassifDTO();
		response.setCustomerClassificationId(saved.getCustomerClassificationId());
		response.setCustomerClassificationType(saved.getCustomerClassificationType());
		response.setCustomerClassificationValue(saved.getCustomerClassificationValue());
		response.setEffectiveDate(saved.getEffectiveDate());

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> deleteClassification(Long id) {

		if (classificationRepo.existsById(id) != true)
			return new ResponseEntity<>(Map.of("message", "Classification ID Not present"), HttpStatus.NOT_FOUND);

		CustomerClassificationType customerClassificationType = classificationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not found"));
		if (Objects.equals(customerClassificationType.getCrudval(), 'D'))
			return new ResponseEntity<>(Map.of("message", "Classification already deleted"), HttpStatus.BAD_REQUEST);

		customerClassificationType.setCrudval('D');
		classificationRepo.save(customerClassificationType);
		return new ResponseEntity<>(Map.of("message", "Deleted successfully"), HttpStatus.OK);

	}

	// private List<CustomerDetail> getListCustomerDetails(Long classifId) {
	// return
	// customerDetailRepo.findByClassificationType_CustomerClassificationId(classifId);
	//
	//
	// }
	//
	// private custClassifDTO convertToDTO(CustomerClassificationType entity) {
	//
	// custClassifDTO dto = new custClassifDTO();
	//
	// dto.setCustomerClassificationId(entity.getCustomerClassificationId());
	// dto.setCustomerClassificationType(entity.getCustomerClassificationType());
	// dto.setCustomerClassificationValue(entity.getCustomerClassificationValue());
	//// dto.setCustomerDetails(entity.getCustomerDetails());
	// dto.setEffectiveDate(entity.getEffectiveDate());
	//
	// return dto;
	// }
	// public static custClassifDTO toDTO(CustomerClassificationType entity) {
	//
	// custClassifDTO dto = new custClassifDTO();
	//
	// dto.setCustomerClassificationId(entity.getCustomerClassificationId());
	// dto.setCustomerClassificationType(entity.getCustomerClassificationType());
	// dto.setCustomerClassificationValue(entity.getCustomerClassificationValue());
	// dto.setEffectiveDate(entity.getEffectiveDate());
	//
	//
	// if (entity.getCustomerDetails() != null) {
	// dto.setCustomerDetails(
	// entity.getCustomerDetails()
	// .stream()
	// .map(CustDetailMapper::toDTO)
	// .toList()
	// );
	// }
	//
	// return dto;
	// }

}
