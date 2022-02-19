package com.chew.adeline.cinemaapp.repository;

import com.chew.adeline.cinemaapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
