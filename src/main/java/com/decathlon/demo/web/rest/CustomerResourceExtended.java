package com.decathlon.demo.web.rest;

import com.decathlon.demo.domain.Customer;
import com.decathlon.demo.service.CustomerServiceExtended;
import com.decathlon.demo.service.dto.CustomerCriteria;
import com.decathlon.demo.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/extended")
public class CustomerResourceExtended {

    private CustomerServiceExtended customerServiceExtended;

    public CustomerResourceExtended(CustomerServiceExtended customerServiceExtended) {
        this.customerServiceExtended = customerServiceExtended;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(Pageable pageable) {
        Page<Customer> page = customerServiceExtended.findAllWithOrders(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/extended/customers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}




