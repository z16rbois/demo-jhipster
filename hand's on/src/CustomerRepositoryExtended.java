package com.decathlon.demo.repository;

import com.decathlon.demo.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryExtended extends CustomerRepository{



    @Query(value = "select c from Customer c inner join fetch c.orders",
        countQuery = "select count(c) from Customer c inner join c.orders")
    Page<Customer> findAllWithOrders(Pageable page);




}




