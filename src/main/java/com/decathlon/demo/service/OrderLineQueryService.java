package com.decathlon.demo.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.decathlon.demo.domain.OrderLine;
import com.decathlon.demo.domain.*; // for static metamodels
import com.decathlon.demo.repository.OrderLineRepository;
import com.decathlon.demo.service.dto.OrderLineCriteria;

/**
 * Service for executing complex queries for OrderLine entities in the database.
 * The main input is a {@link OrderLineCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderLine} or a {@link Page} of {@link OrderLine} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderLineQueryService extends QueryService<OrderLine> {

    private final Logger log = LoggerFactory.getLogger(OrderLineQueryService.class);

    private final OrderLineRepository orderLineRepository;

    public OrderLineQueryService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    /**
     * Return a {@link List} of {@link OrderLine} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderLine> findByCriteria(OrderLineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrderLine> specification = createSpecification(criteria);
        return orderLineRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link OrderLine} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderLine> findByCriteria(OrderLineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrderLine> specification = createSpecification(criteria);
        return orderLineRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrderLineCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrderLine> specification = createSpecification(criteria);
        return orderLineRepository.count(specification);
    }

    /**
     * Function to convert OrderLineCriteria to a {@link Specification}
     */
    private Specification<OrderLine> createSpecification(OrderLineCriteria criteria) {
        Specification<OrderLine> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderLine_.id));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), OrderLine_.quantity));
            }
            if (criteria.getLabel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLabel(), OrderLine_.label));
            }
            if (criteria.getUnitPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUnitPrice(), OrderLine_.unitPrice));
            }
            if (criteria.getOrderId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrderId(),
                    root -> root.join(OrderLine_.order, JoinType.LEFT).get(Order_.id)));
            }
        }
        return specification;
    }
}
