package com.example.demo.utils;

import com.example.demo.DTO.custContactInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ContactInfoValidator {

    public static String validate(custContactInfoDTO dto) {
        String type = dto.getCustomerContactType();
        String value = dto.getCustomerContactValue();

        if (type != null && value != null) {
            if (type.equalsIgnoreCase("EMAIL")) {
                if (value.contains(" ")) {
                    return "Email should have no whitespaces";
                }
                if (!value.contains("@")) {
                    return "Email should have the @ symbol";
                }
            } else if (type.equalsIgnoreCase("MOBILE") || type.equalsIgnoreCase("PHONE")) {
                if (!value.matches("\\d{10}")) {
                    return "Phone number should have only 10 numbers";
                }
            }
        }
        return null; // Passes
    }

    public static ResponseEntity<String> validateToResponse(custContactInfoDTO dto) {
        String error = validate(dto);
        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
