package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerDetail;

public interface CustomerDetailRepo extends JpaRepository<CustomerDetail,Long>{

	List<CustomerDetail> findByClassificationType_CustomerClassificationId(Long id);

}
