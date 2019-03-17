package com.decathlon.demo.service;

import com.decathlon.demo.domain.Customer;
import com.decathlon.demo.repository.CustomerRepository;
import com.decathlon.demo.repository.CustomerRepositoryExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerServiceExtended extends CustomerService{

    private CustomerRepositoryExtended customerRepository;

    public CustomerServiceExtended(CustomerRepository customerRepository, CustomerRepositoryExtended customerRepositoryExtended) {
        super(customerRepository);
        this.customerRepository = customerRepositoryExtended;
    }

    @Transactional(readOnly = true)
    public Page<Customer> findAllWithOrders(Pageable pageable) {
        return customerRepository.findAllWithOrders(pageable);
    }
}



