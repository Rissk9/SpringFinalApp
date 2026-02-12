package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerName;

public interface CustomerNameRepo extends JpaRepository<CustomerName,Long> {

}
