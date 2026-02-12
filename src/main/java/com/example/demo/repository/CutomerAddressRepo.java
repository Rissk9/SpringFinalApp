package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerAddress;

public interface CutomerAddressRepo extends JpaRepository<CustomerAddress,Long> {

}