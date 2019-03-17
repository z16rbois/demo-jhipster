# Demo JHipster

## Step3

### Customisation avec héritage

Dans postman lancer un GET Customers :

Les customers retournés ont le champ order : null bien qu'ils possèdent des commandes.
Cela est dû au fetchType Lazy positionné sur la relation orders de l'entité Customer

Nous allons créer un nouveau endpoint pour retourner les customers ainsi que leurs orders

#### Repository

Créer le nouveau Repository "CustomerRepositoryExtended" dans le package "/src/main/java/com/decathlon/demo/repository"

```java
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

```

#### Service

Créer le nouveau service "CustomerServiceExtended" dans le package "src/main/java/com/decathlon/demo/service"

```java
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
```

#### Resouce

Créer le nouveau fichier de resource "CustomerResourceExtended" dans la package "src/main/java/com/decathlon/demo/web/rest"

```java
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

```

Lancer l'application

Dans postman lancer la requete GET "Customers With Orders"

Ajouter une nouvelle colonne "name" de type "String" dans l'entité order du fichier entities.jdl

Lancer la commande `jhipster import-jdl entities.jdl`

Redemarrer l'application

Dans postman lancer la requete GET "Customers With Orders"
