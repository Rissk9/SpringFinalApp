package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.custAddressDTO;
import com.example.demo.DTO.custContactInfoDTO;
import com.example.demo.DTO.custDetailDTO;
import com.example.demo.DTO.custNameDTO;
import com.example.demo.DTO.custProofOfIdDTO;
import com.example.demo.entity.CustomerDetail;
import com.example.demo.entity.CustomerIdentification;
import com.example.demo.entity.CustomerName;
import com.example.demo.entity.CustomerProofofId;
import com.example.demo.mapping.CustDetailMapper;
import com.example.demo.entity.CustomerAddress;
import com.example.demo.entity.CustomerClassificationType;
import com.example.demo.entity.CustomerContactInformation;
import com.example.demo.repository.CustomerClassificationRepo;
import com.example.demo.repository.CustomerDetailRepo;
import com.example.demo.repository.CustomerProofofIdRepo;
import com.example.demo.repository.CutomerAddressRepo;
import com.example.demo.service.CustomerDetailsService;
import com.example.demo.utils.ContactInfoValidator;

@Service
public class custDetailsServiceImpl implements CustomerDetailsService {

	private final CustomerProofofIdRepo customerProofofIdRepo;

	@Autowired
	private CustomerDetailRepo repo;

	@Autowired
	private CustomerClassificationRepo classificationRepo;

	@Autowired
	private CutomerAddressRepo addressRepo;

	custDetailsServiceImpl(CustomerProofofIdRepo customerProofofIdRepo) {
		this.customerProofofIdRepo = customerProofofIdRepo;
	}

	// @Override
	// public ResponseEntity<CustomerDetail> addCustomer(custDetailDTO custbody) {
	// CustomerDetail custDetailEntity=new CustomerDetail(custbody);
	//// custDetailEntity.setCustomerId(custbody.getCustomerId());
	//// custDetailEntity.setCustFullname(custbody.getCustFullname());
	//// custDetailEntity.setCustGender(custbody.getCustGender());
	//// custDetailEntity.setCustDate(custbody.getCustDate());
	//// custDetailEntity.setCustPrefLanguage(custbody.getCustPrefLanguage());
	//// custDetailEntity.setCustStatus(custbody.getCustStatus());
	//// custDetailEntity.setCust_country(custbody.getCust_country());
	//// custDetailEntity.setCustomerAddresses(custbody.getCustomerAddresses());
	//// custDetailEntity.setCustomerNames(custbody.getCustomerNames());
	//// custDetailEntity.setCustomerContactInformations(custbody.getCustomerContactInformations());
	//// custDetailEntity.setCustomerProofofIds(custbody.getCustomerProofofIds());
	//// custDetailEntity.setClassificationType(custDetailDTO.toEntity(custbody.getClassifDTO()));
	//// custDetailEntity.setCustomerIdentification(custDetailDTO.toEntity(custbody.getCustIndentifDTO()));
	//
	// CustomerDetail saved=repo.save(custDetailEntity);
	// return new ResponseEntity<CustomerDetail>(saved,HttpStatus.ACCEPTED);
	//
	// }

	@Override
	public ResponseEntity<?> addCustomer(custDetailDTO dto) {

		CustomerDetail customer = new CustomerDetail();
		customer.setCustFullname(dto.getCustFullname());
		customer.setCustGender(dto.getCustGender());
		customer.setCustDate(dto.getCustDate());
		customer.setCustPrefLanguage(dto.getCustPrefLanguage());
		customer.setCustStatus(dto.getCustStatus());
		customer.setCust_country(dto.getCustCountry());
		// customer.setIsdeleted(null);

		CustomerClassificationType classification = classificationRepo.findById(dto.getClassificationId())
				.orElseThrow(() -> new RuntimeException("Invalid classification id"));
		customer.setClassificationType(classification);

		if (dto.getIdentification() != null) {
			CustomerIdentification ident = new CustomerIdentification();
			ident.setCustIdentificationtype(
					dto.getIdentification().getCustIdentificationtype());
			ident.setCustIdentificationItem(
					dto.getIdentification().getCustIdentificationItem());
			ident.setEffectiveDate(
					dto.getIdentification().getEffectiveDate());

			ident.setCustomerDetail_FK(customer); // FK owner
			customer.setCustomerIdentification(ident);
		}
		if (dto.getCustomerNames() != null) {
			List<CustomerName> custnames = new ArrayList<>();
			for (custNameDTO n : dto.getCustomerNames()) {
				CustomerName tempCustomerName = new CustomerName();
				CustomerClassificationType classif = classificationRepo.findById(dto.getClassificationId())
						.orElseThrow(() -> new RuntimeException("Invalid id"));

				tempCustomerName.setClassificationType_FK_Custname(classif);

				tempCustomerName.setCustomerDetail_FK_Custname(customer);
				// tempCustomerName.setCustNameId(n.getCustNameId());
				tempCustomerName.setCustomerNameType(n.getCustomerNameType());
				tempCustomerName.setCustomerNameValue(n.getCustomerNameValue());
				tempCustomerName.setEffectiveDate(n.getEffectiveDate());

				custnames.add(tempCustomerName);
			}
			customer.setCustomerNames(custnames);
		}

		if (dto.getCustomerProofofIds() != null) {
			List<CustomerProofofId> custproof = new ArrayList<>();
			for (custProofOfIdDTO n : dto.getCustomerProofofIds()) {
				CustomerProofofId ctemp = new CustomerProofofId();
				// ctemp.setCustomerProofId(n.getCustomerProofId());
				ctemp.setProofofIdType(n.getProofofIdType());
				ctemp.setProofofIdValue(n.getProofofIdValue());
				ctemp.setEffectivDate(n.getEffectivDate());
				ctemp.setStartDate(n.getStartDate());
				ctemp.setEndDate(n.getEndDate());
				ctemp.setCustomerDetail_fk_proofofId(customer);

				custproof.add(ctemp);
			}
			customer.setCustomerProofofIds(custproof);
		}
		if (dto.getCustomerContactInformations() != null) {
			List<CustomerContactInformation> custcontact = new ArrayList<>();
			for (custContactInfoDTO n : dto.getCustomerContactInformations()) {

				// Validate individual contact info
				ResponseEntity<String> validationResponse = ContactInfoValidator.validateToResponse(n);
				if (validationResponse != null) {
					return validationResponse;
				}

				CustomerContactInformation temp = new CustomerContactInformation();
				// temp.setCustContactId(n.getCustContactId());
				temp.setCustomerContactType(n.getCustomerContactType());
				temp.setCustomerContactValue(n.getCustomerContactValue());
				temp.setCustomerDetail(customer);
				temp.setEffectiveDate(n.getEffectiveDate());
				temp.setEndDate(n.getEndDate());
				temp.setStartDate(n.getStartDate());
				custcontact.add(temp);
			}
			customer.setCustomerContactInformations(custcontact);
		}
		if (dto.getCustomerAddresses() != null) {
			List<CustomerAddress> addresses = new ArrayList<>();

			for (custAddressDTO n : dto.getCustomerAddresses()) {
				CustomerAddress temp = new CustomerAddress();
				temp.setCustomerAddressType(n.getAddressType());
				temp.setCustomerAddressValue(n.getAddressValue());
				temp.setEffectiveDate(n.getEffectiveDate());
				CustomerClassificationType c = classificationRepo.findById(dto.getClassificationId())
						.orElseThrow(() -> new RuntimeException("Classification id not found"));

				temp.setCustomerClassificationType_FK_custAddress(c);
				temp.setCustomerDetail_FK_custAddress(customer);

				addresses.add(temp);
			}
			customer.setCustomerAddresses(addresses);
		}

		CustomerDetail saved = repo.save(customer);
		custDetailDTO response = CustDetailMapper.toDTO(saved);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<custDetailDTO>> getAllCustomers() {
		List<CustomerDetail> entities = repo.findAll();
		List<custDetailDTO> customers = new ArrayList<>();

		for (CustomerDetail entity : entities) {
			if (Objects.equals('D', entity.getCrudval()) == false) {
				custDetailDTO dto = CustDetailMapper.toDTO(entity);
				customers.add(dto);
			}
		}

		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// @Override
	// public ResponseEntity<custDetailDTO> updateCustomer(custDetailDTO dto) {
	//
	// CustomerDetail existing=repo.findById(dto.getCustId()).orElseThrow(()->new
	// RuntimeException("Id not found in the database"));
	//
	// CustomerClassificationType
	// classification=classificationRepo.findById(dto.getClassificationId()).orElseThrow(()->new
	// RuntimeException("Classification ID not present in the database"));
	// existing.setClassificationType(classification);
	// existing.setCust_country(dto.getCustCountry());
	// existing.setCustDate(dto.getCustDate());
	// existing.setCustFullname(dto.getCustFullname());
	// existing.setCustGender(dto.getCustGender());
	// existing.setCustPrefLanguage(dto.getCustPrefLanguage());
	//
	//
	//// if(dto.getCustomerAddresses()!=null) {
	//// List<CustomerAddress> addresses=new ArrayList<>();
	////
	//// for(custAddressDTO n:dto.getCustomerAddresses()) {
	//// CustomerAddress temp;
	//// temp=addressRepo.findById(n.getCustAddressId()).orElseThrow(()->new
	// RuntimeException("Customer address id not found"));
	////
	//// temp.setCustomerAddressType(n.getAddressType());
	//// temp.setCustomerAddressValue(n.getAddressValue());
	//// temp.setEffectiveDate(n.getEffectiveDate());
	//// CustomerClassificationType
	// c=classificationRepo.findById(dto.getClassificationId())
	//// .orElseThrow(()-> new RuntimeException("Classification id not found"));
	////
	//// temp.setCustomerClassificationType_FK_custAddress(c);
	//// temp.setCustomerDetail_FK_custAddress(existing);
	//// temp.setCrudval('U');
	//// addresses.add(temp);
	//// }
	//// existing.setCustomerAddresses(addresses);
	//// }
	// if (dto.getCustomerAddresses() != null) {
	//
	// List<CustomerAddress> addresses = new ArrayList<>();
	//
	// for (custAddressDTO n : dto.getCustomerAddresses()) {
	//
	// CustomerAddress temp;
	//
	// if (n.getCustAddressId() != null) {
	// // UPDATE existing
	// temp = addressRepo.findById(n.getCustAddressId())
	// .orElseThrow(() -> new RuntimeException("Customer address id not found"));
	// temp.setCrudval('U');
	// } else {
	// // CREATE new
	// temp = new CustomerAddress();
	// temp.setCustomerDetail_FK_custAddress(existing);
	// temp.setCrudval('C');
	// }
	//
	// temp.setCustomerAddressType(n.getAddressType());
	// temp.setCustomerAddressValue(n.getAddressValue());
	// temp.setEffectiveDate(n.getEffectiveDate());
	//
	// temp.setCustomerClassificationType_FK_custAddress(classification);
	//
	// addresses.add(temp);
	// }
	//
	// existing.getCustomerAddresses().clear();
	// existing.getCustomerAddresses().addAll(addresses);
	// }
	//
	// if(dto.getCustomerContactInformations()!=null) {
	// List <CustomerContactInformation> custcontact=new ArrayList<>();
	// for(custContactInfoDTO n:dto.getCustomerContactInformations()) {
	// CustomerContactInformation temp=new CustomerContactInformation();
	//// temp.setCustContactId(n.getCustContactId());
	// temp.setCustomerContactType(n.getCustomerContactType());
	// temp.setCustomerContactValue(n.getCustomerContactValue());
	// temp.setCustomerDetail(existing);
	// temp.setEffectiveDate(n.getEffectiveDate());
	// temp.setEndDate(n.getEndDate());
	// temp.setStartDate(n.getStartDate());
	// temp.setCrudval('U');
	// custcontact.add(temp);
	// }
	// existing.setCustomerContactInformations(custcontact);
	// }
	// if(dto.getCustomerProofofIds()!=null) {
	// List <CustomerProofofId> custproof=new ArrayList<>();
	// for(custProofOfIdDTO n:dto.getCustomerProofofIds()) {
	// CustomerProofofId ctemp=new CustomerProofofId();
	//// ctemp.setCustomerProofId(n.getCustomerProofId());
	// ctemp.setProofofIdType(n.getProofofIdType());
	// ctemp.setProofofIdValue(n.getProofofIdValue());
	// ctemp.setEffectivDate(n.getEffectivDate());
	// ctemp.setStartDate(n.getStartDate());
	// ctemp.setEndDate(n.getEndDate());
	// ctemp.setCustomerDetail_fk_proofofId(existing);
	// ctemp.setCrudval('U');
	// custproof.add(ctemp);
	// }
	// existing.setCustomerProofofIds(custproof);
	// }
	// if (dto.getIdentification() != null) {
	//// CustomerIdentification ident = new CustomerIdentification();
	//// ident.setCustIdentificationtype(
	//// dto.getIdentification().getCustIdentificationtype());
	//// ident.setCustIdentificationItem(
	//// dto.getIdentification().getCustIdentificationItem());
	//// ident.setEffectiveDate(
	//// dto.getIdentification().getEffectiveDate());
	////
	//// ident.setCustomerDetail_FK(existing); // FK owner
	//// existing.setCustomerIdentification(ident);
	//
	// CustomerIdentification ident = existing.getCustomerIdentification();
	//
	// // if already present → UPDATE
	// if (ident == null) {
	// ident = new CustomerIdentification();
	// ident.setCustomerDetail_FK(existing);
	// }
	//
	// ident.setCustIdentificationtype(dto.getIdentification().getCustIdentificationtype());
	// ident.setCustIdentificationItem(dto.getIdentification().getCustIdentificationItem());
	// ident.setEffectiveDate(dto.getIdentification().getEffectiveDate());
	//
	// ident.setCrudval('U');
	// existing.setCustomerIdentification(ident);
	// }
	//
	// if(dto.getCustomerNames()!=null) {
	// List<CustomerName> custnames=new ArrayList<>();
	// for(custNameDTO n:dto.getCustomerNames()) {
	// CustomerName tempCustomerName=new CustomerName();
	// CustomerClassificationType classif=classification;
	//
	// tempCustomerName.setClassificationType_FK_Custname(classif);
	//
	// tempCustomerName.setCustomerDetail_FK_Custname(existing);
	//// tempCustomerName.setCustNameId(n.getCustNameId());
	// tempCustomerName.setCustomerNameType(n.getCustomerNameType());
	// tempCustomerName.setCustomerNameValue(n.getCustomerNameValue());
	// tempCustomerName.setEffectiveDate(n.getEffectiveDate());
	// tempCustomerName.setCrudval('U');
	// custnames.add(tempCustomerName);
	// }
	// existing.setCustomerNames(custnames);
	// }
	// existing.setCrudval('U');
	// CustomerDetail ctemp=repo.save(existing);
	// return new
	// ResponseEntity<custDetailDTO>(CustDetailMapper.toDTO(ctemp),HttpStatus.OK);
	// }

	@Override
	public ResponseEntity<?> updateCustomer(custDetailDTO dto) {

		CustomerDetail existing = repo.findById(dto.getCustId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		if (Objects.equals('D', existing.getCrudval()))
			return new ResponseEntity<String>("Element already deleted", HttpStatus.NOT_FOUND);

		CustomerClassificationType classification = classificationRepo.findById(dto.getClassificationId())
				.orElseThrow(() -> new RuntimeException("Classification not found"));

		// ================= BASIC FIELDS =================
		existing.setCustFullname(dto.getCustFullname());
		existing.setCustGender(dto.getCustGender());
		existing.setCustDate(dto.getCustDate());
		existing.setCustPrefLanguage(dto.getCustPrefLanguage());
		existing.setCustStatus(dto.getCustStatus());
		existing.setCust_country(dto.getCustCountry());
		existing.setClassificationType(classification);
		existing.setCrudval('U');

		// ================= ADDRESSES =================
		if (dto.getCustomerAddresses() != null) {

			List<CustomerAddress> updatedAddresses = new ArrayList<>();

			for (custAddressDTO n : dto.getCustomerAddresses()) {

				CustomerAddress address;

				if (n.getCustAddressId() != null) {
					address = addressRepo.findById(n.getCustAddressId())
							.orElseThrow(() -> new RuntimeException("Address not found"));
					address.setCrudval('U');
				} else {
					address = new CustomerAddress();
					address.setCustomerDetail_FK_custAddress(existing);
					address.setCrudval('C');
				}

				address.setCustomerAddressType(n.getAddressType());
				address.setCustomerAddressValue(n.getAddressValue());
				address.setEffectiveDate(n.getEffectiveDate());
				address.setCustomerClassificationType_FK_custAddress(classification);

				updatedAddresses.add(address);
			}

			existing.getCustomerAddresses().clear();
			existing.getCustomerAddresses().addAll(updatedAddresses);
		}

		// ================= CONTACTS =================
		if (dto.getCustomerContactInformations() != null) {

			List<CustomerContactInformation> updatedContacts = new ArrayList<>();

			for (custContactInfoDTO n : dto.getCustomerContactInformations()) {

				// Validate individual contact info
				ResponseEntity<String> validationResponse = ContactInfoValidator.validateToResponse(n);
				if (validationResponse != null) {
					return validationResponse;
				}

				CustomerContactInformation contact;

				if (n.getCustContactId() != null) {
					contact = existing.getCustomerContactInformations()
							.stream()
							.filter(c -> c.getCustContactId().equals(n.getCustContactId()))
							.findFirst()
							.orElseThrow(() -> new RuntimeException("Contact not found"));
					contact.setCrudval('U');
				} else {
					contact = new CustomerContactInformation();
					contact.setCustomerDetail(existing);
					contact.setCrudval('C');
				}

				contact.setCustomerContactType(n.getCustomerContactType());
				contact.setCustomerContactValue(n.getCustomerContactValue());
				contact.setEffectiveDate(n.getEffectiveDate());
				contact.setStartDate(n.getStartDate());
				contact.setEndDate(n.getEndDate());

				updatedContacts.add(contact);
			}

			existing.getCustomerContactInformations().clear();
			existing.getCustomerContactInformations().addAll(updatedContacts);
		}

		// ================= PROOF OF ID =================
		if (dto.getCustomerProofofIds() != null) {

			List<CustomerProofofId> updatedProofs = new ArrayList<>();

			for (custProofOfIdDTO n : dto.getCustomerProofofIds()) {

				CustomerProofofId proof;

				if (n.getCustomerProofId() != null) {
					proof = existing.getCustomerProofofIds()
							.stream()
							.filter(p -> p.getCustomerProofId() == (n.getCustomerProofId()))
							.findFirst()
							.orElseThrow(() -> new RuntimeException("Proof not found"));
					proof.setCrudval('U');
				} else {
					proof = new CustomerProofofId();
					proof.setCustomerDetail_fk_proofofId(existing);
					proof.setCrudval('C');
				}

				proof.setProofofIdType(n.getProofofIdType());
				proof.setProofofIdValue(n.getProofofIdValue());
				proof.setEffectivDate(n.getEffectivDate());
				proof.setStartDate(n.getStartDate());
				proof.setEndDate(n.getEndDate());

				updatedProofs.add(proof);
			}

			existing.getCustomerProofofIds().clear();
			existing.getCustomerProofofIds().addAll(updatedProofs);
		}

		// ================= NAMES =================
		if (dto.getCustomerNames() != null) {

			List<CustomerName> updatedNames = new ArrayList<>();

			for (custNameDTO n : dto.getCustomerNames()) {

				CustomerName name;

				if (n.getCustNameId() != null) {
					name = existing.getCustomerNames()
							.stream()
							.filter(x -> x.getCustNameId().equals(n.getCustNameId()))
							.findFirst()
							.orElseThrow(() -> new RuntimeException("Name not found"));
					name.setCrudval('U');
				} else {
					name = new CustomerName();
					name.setCustomerDetail_FK_Custname(existing);
					name.setCrudval('C');
				}

				name.setCustomerNameType(n.getCustomerNameType());
				name.setCustomerNameValue(n.getCustomerNameValue());
				name.setEffectiveDate(n.getEffectiveDate());
				name.setClassificationType_FK_Custname(classification);

				updatedNames.add(name);
			}

			existing.getCustomerNames().clear();
			existing.getCustomerNames().addAll(updatedNames);
		}

		// ================= IDENTIFICATION =================
		if (dto.getIdentification() != null) {

			CustomerIdentification ident = existing.getCustomerIdentification();

			if (ident == null) {
				ident = new CustomerIdentification();
				ident.setCustomerDetail_FK(existing);
				ident.setCrudval('C');
			} else {
				ident.setCrudval('U');
			}

			ident.setCustIdentificationtype(dto.getIdentification().getCustIdentificationtype());
			ident.setCustIdentificationItem(dto.getIdentification().getCustIdentificationItem());
			ident.setEffectiveDate(dto.getIdentification().getEffectiveDate());

			existing.setCustomerIdentification(ident);
		}

		CustomerDetail saved = repo.save(existing);

		return new ResponseEntity<>(CustDetailMapper.toDTO(saved), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteCustomer(Long custId) {

		if (repo.existsById(custId) != true) {
			return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
		}

		CustomerDetail customerDetail = repo.findById(custId)
				.orElseThrow(() -> new RuntimeException("The id is not found"));

		if (Objects.equals(customerDetail.getCrudval(), 'D'))
			return new ResponseEntity<>(Map.of("message", "Customer already deleted"), HttpStatus.BAD_REQUEST);

		customerDetail.setCrudval('D');
		for (CustomerAddress ad : customerDetail.getCustomerAddresses()) {
			ad.setCrudval('D');
		}

		for (CustomerContactInformation contact : customerDetail.getCustomerContactInformations()) {
			contact.setCrudval('D');
		}

		if (customerDetail.getCustomerIdentification() != null) {
			customerDetail.getCustomerIdentification().setCrudval('D');
		}

		for (CustomerName names : customerDetail.getCustomerNames()) {
			names.setCrudval('D');
		}

		for (CustomerProofofId proofofId : customerDetail.getCustomerProofofIds()) {
			proofofId.setCrudval('D');
		}

		repo.save(customerDetail);

		return new ResponseEntity<>(Map.of("message", "Deleted successfully"), HttpStatus.OK);
	}

}
