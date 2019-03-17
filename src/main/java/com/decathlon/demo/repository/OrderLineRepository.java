package com.decathlon.demo.repository;

import com.decathlon.demo.domain.OrderLine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>, JpaSpecificationExecutor<OrderLine> {

}
