package com.decathlon.demo.web.rest;
import com.decathlon.demo.domain.OrderLine;
import com.decathlon.demo.service.OrderLineService;
import com.decathlon.demo.web.rest.errors.BadRequestAlertException;
import com.decathlon.demo.web.rest.util.HeaderUtil;
import com.decathlon.demo.web.rest.util.PaginationUtil;
import com.decathlon.demo.service.dto.OrderLineCriteria;
import com.decathlon.demo.service.OrderLineQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrderLine.
 */
@RestController
@RequestMapping("/api")
public class OrderLineResource {

    private final Logger log = LoggerFactory.getLogger(OrderLineResource.class);

    private static final String ENTITY_NAME = "orderLine";

    private final OrderLineService orderLineService;

    private final OrderLineQueryService orderLineQueryService;

    public OrderLineResource(OrderLineService orderLineService, OrderLineQueryService orderLineQueryService) {
        this.orderLineService = orderLineService;
        this.orderLineQueryService = orderLineQueryService;
    }

    /**
     * POST  /order-lines : Create a new orderLine.
     *
     * @param orderLine the orderLine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderLine, or with status 400 (Bad Request) if the orderLine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-lines")
    public ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine) throws URISyntaxException {
        log.debug("REST request to save OrderLine : {}", orderLine);
        if (orderLine.getId() != null) {
            throw new BadRequestAlertException("A new orderLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderLine result = orderLineService.save(orderLine);
        return ResponseEntity.created(new URI("/api/order-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-lines : Updates an existing orderLine.
     *
     * @param orderLine the orderLine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderLine,
     * or with status 400 (Bad Request) if the orderLine is not valid,
     * or with status 500 (Internal Server Error) if the orderLine couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-lines")
    public ResponseEntity<OrderLine> updateOrderLine(@RequestBody OrderLine orderLine) throws URISyntaxException {
        log.debug("REST request to update OrderLine : {}", orderLine);
        if (orderLine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderLine result = orderLineService.save(orderLine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderLine.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-lines : get all the orderLines.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderLines in body
     */
    @GetMapping("/order-lines")
    public ResponseEntity<List<OrderLine>> getAllOrderLines(OrderLineCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderLines by criteria: {}", criteria);
        Page<OrderLine> page = orderLineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-lines");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /order-lines/count : count all the orderLines.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/order-lines/count")
    public ResponseEntity<Long> countOrderLines(OrderLineCriteria criteria) {
        log.debug("REST request to count OrderLines by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderLineQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /order-lines/:id : get the "id" orderLine.
     *
     * @param id the id of the orderLine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderLine, or with status 404 (Not Found)
     */
    @GetMapping("/order-lines/{id}")
    public ResponseEntity<OrderLine> getOrderLine(@PathVariable Long id) {
        log.debug("REST request to get OrderLine : {}", id);
        Optional<OrderLine> orderLine = orderLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderLine);
    }

    /**
     * DELETE  /order-lines/:id : delete the "id" orderLine.
     *
     * @param id the id of the orderLine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-lines/{id}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable Long id) {
        log.debug("REST request to delete OrderLine : {}", id);
        orderLineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
