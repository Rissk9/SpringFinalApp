package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.custAddressDTO;
import com.example.demo.DTO.custContactInfoDTO;
import com.example.demo.DTO.custDetailDTO;
import com.example.demo.DTO.custIndentifDTO;
import com.example.demo.DTO.custNameDTO;
import com.example.demo.DTO.custProofOfIdDTO;
import com.example.demo.service.CustomerDetailsService;

@Service
public class CustomerBulkService {

    @Autowired
    private CustomerDetailsService custDetailsService;

    public List<String> processFile(MultipartFile file) {
        List<String> errors = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                	if (row.getCell(0) == null) {
                	    throw new RuntimeException("Name missing");
                	}
                    custDetailDTO dto = mapRowToDTO(row);
                    custDetailsService.addCustomer(dto);
                } catch (Exception e) {
                    errors.add("Row " + i + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            errors.add("File error: " + e.getMessage());
        }

        return errors;
    }

    private custDetailDTO mapRowToDTO(Row row) {
        custDetailDTO dto = new custDetailDTO();

        // BASIC FIELDS
        dto.setCustFullname(getString(row.getCell(0)));
        dto.setCustGender(getString(row.getCell(1)));
        dto.setCustDate(getDate(row.getCell(2)));
        dto.setCustPrefLanguage(getString(row.getCell(3)));
        dto.setCustStatus(getString(row.getCell(4)));
        dto.setCustCountry(getString(row.getCell(5)));
        dto.setClassificationId(getLong(row.getCell(6)));

        // ADDRESS
        custAddressDTO address = new custAddressDTO();
        address.setAddressType(getString(row.getCell(7)));
        address.setAddressValue(getString(row.getCell(8)));
        address.setEffectiveDate(getDate(row.getCell(9)));
        dto.setCustomerAddresses(List.of(address));

        // CONTACT
        custContactInfoDTO contact = new custContactInfoDTO();
        contact.setCustomerContactType(getString(row.getCell(10)));
        contact.setCustomerContactValue(getString(row.getCell(11)));
        contact.setStartDate(getDate(row.getCell(12)));
        contact.setEndDate(getDate(row.getCell(13)));
        dto.setCustomerContactInformations(List.of(contact));

        // PROOF
        custProofOfIdDTO proof = new custProofOfIdDTO();
        proof.setProofofIdType(getString(row.getCell(14)));
        proof.setProofofIdValue(getString(row.getCell(15)));
        proof.setStartDate(getDate(row.getCell(16)));
        proof.setEndDate(getDate(row.getCell(17)));
        dto.setCustomerProofofIds(List.of(proof));

        // NAME
        custNameDTO name = new custNameDTO();
        name.setCustomerNameType(getString(row.getCell(18)));
        name.setCustomerNameValue(getString(row.getCell(19)));
        dto.setCustomerNames(List.of(name));

        // IDENTIFICATION
        custIndentifDTO identification = new custIndentifDTO();
        identification.setCustIdentificationtype(getString(row.getCell(20)));
        identification.setCustIdentificationItem(getString(row.getCell(21)));
        identification.setEffectiveDate(getDate(row.getCell(22)));
        dto.setIdentification(identification);

        return dto;
    }
    
    private String getString(Cell cell) {
    	if (cell == null) return null;
    	try {
        	return cell.getStringCellValue();
    	} catch (IllegalStateException e) {
    		return String.valueOf(cell.getNumericCellValue()); // Fallback if number
    	}
    }
    
    private LocalDate getDate(Cell cell) {
    	if (cell == null) return null;
    	return cell.getLocalDateTimeCellValue().toLocalDate();
    }
    
    private Long getLong(Cell cell) {
    	if (cell == null) return null;
    	return (long) cell.getNumericCellValue();
    }
}
