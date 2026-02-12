//package com.example.demo.mapping;
//
//import com.example.demo.DTO.custDetailDTO;
//import com.example.demo.entity.CustomerDetail;
//
//public class CustDetailMapper {
//
//	public static custDetailDTO toDTO(CustomerDetail entity) {
//	    custDetailDTO dto = new custDetailDTO();
//	    dto.setCustId(entity.getCustomerId());
//	    dto.setCustFullname(entity.getCustFullname());
//	    dto.setCustGender(entity.getCustGender());
//	    dto.setCustDate(entity.getCustDate());
//	    dto.setCustPrefLanguage(entity.getCustPrefLanguage());
//	    dto.setCustStatus(entity.getCustStatus());
//	    dto.setCustCountry(entity.getCust_country());
//
//	    dto.setClassificationId(
//	        entity.getClassificationType().getCustomerClassificationId());
//
//	    return dto;
//	}
//}
package com.example.demo.mapping;

import java.util.stream.Collectors;

import com.example.demo.DTO.*;
import com.example.demo.entity.*;

public class CustDetailMapper {

    public static custDetailDTO toDTO(CustomerDetail entity) {

        custDetailDTO dto = new custDetailDTO();

        // ---------------- BASIC FIELDS ----------------
        dto.setCustId(entity.getCustomerId());
        dto.setCustFullname(entity.getCustFullname());
        dto.setCustGender(entity.getCustGender());
        dto.setCustDate(entity.getCustDate());
        dto.setCustPrefLanguage(entity.getCustPrefLanguage());
        dto.setCustStatus(entity.getCustStatus());
        dto.setCustCountry(entity.getCust_country());

        // ---------------- CLASSIFICATION ----------------
        if (entity.getClassificationType() != null) {
            dto.setClassificationId(
                entity.getClassificationType().getCustomerClassificationId()
            );
        }

        // ---------------- IDENTIFICATION (1–1) ----------------
        if (entity.getCustomerIdentification() != null) {
            CustomerIdentification ci = entity.getCustomerIdentification();

            custIndentifDTO idDTO = new custIndentifDTO();
            idDTO.setCustomerIdentifId(ci.getCustomerIdentifId());
            idDTO.setCustIdentificationtype(ci.getCustIdentificationtype());
            idDTO.setCustIdentificationItem(ci.getCustIdentificationItem());
            idDTO.setEffectiveDate(ci.getEffectiveDate());

            dto.setIdentification(idDTO);
        }

        // ---------------- NAMES (1–M) ----------------
        if (entity.getCustomerNames() != null) {
            dto.setCustomerNames(
                entity.getCustomerNames()
                      .stream()
                      .map(n -> {
                          custNameDTO nd = new custNameDTO();
                          nd.setCustNameId(n.getCustNameId());
                          nd.setCustomerNameType(n.getCustomerNameType());
                          nd.setCustomerNameValue(n.getCustomerNameValue());
                          nd.setEffectiveDate(n.getEffectiveDate());
                          return nd;
                      })
                      .collect(Collectors.toList())
            );
        }

        // ---------------- PROOF OF ID (1–M) ----------------
        if (entity.getCustomerProofofIds() != null) {
            dto.setCustomerProofofIds(
                entity.getCustomerProofofIds()
                      .stream()
                      .map(p -> {
                          custProofOfIdDTO pd = new custProofOfIdDTO();
                          pd.setProofofIdType(p.getProofofIdType());
                          pd.setCustomerProofId(p.getCustomerProofId());
                          pd.setProofofIdValue(p.getProofofIdValue());
                          pd.setEffectivDate(p.getEffectivDate());
                          pd.setStartDate(p.getStartDate());
                          pd.setEndDate(p.getEndDate());
                          return pd;
                      })
                      .collect(Collectors.toList())
            );
        }

        // ---------------- CONTACT INFO (1–M) ----------------
        if (entity.getCustomerContactInformations() != null) {
            dto.setCustomerContactInformations(
                entity.getCustomerContactInformations()
                      .stream()
                      .map(c -> {
                          custContactInfoDTO cd = new custContactInfoDTO();
                          cd.setCustomerContactType(c.getCustomerContactType());
                          cd.setCustContactId(c.getCustContactId());
                          cd.setCustomerContactValue(c.getCustomerContactValue());
                          cd.setEffectiveDate(c.getEffectiveDate());
                          return cd;
                      })
                      .collect(Collectors.toList())
            );
        }

        // ---------------- ADDRESSES (1–M) ----------------
        if (entity.getCustomerAddresses() != null) {
            dto.setCustomerAddresses(
                entity.getCustomerAddresses()
                      .stream()
                      .map(a -> {
                          custAddressDTO ad = new custAddressDTO();
//                          ad.setCustomerAddressType(a.getCustomerAddressType());
                          ad.setAddressType(a.getCustomerAddressType());
                          ad.setAddressValue(a.getCustomerAddressValue());
                          ad.setEffectiveDate(a.getEffectiveDate());
                          return ad;
                      })
                      .toList()
            );
        }


        return dto;
    }
}
