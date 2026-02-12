package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerClassificationType;

public interface CustomerClassificationRepo extends JpaRepository<CustomerClassificationType,Long> {
	
}
