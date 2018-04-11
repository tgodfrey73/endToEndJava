package com.godfrey.customer.dal.repos;

import org.springframework.data.repository.CrudRepository;

import com.godfrey.customer.dal.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
