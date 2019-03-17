package com.decathlon.demo.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the Order entity. This class is used in OrderResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /orders?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter date;

    private StringFilter billNumber;

    private DoubleFilter amount;

    private StringFilter status;

    private StringFilter name;

    private LongFilter orderLineId;

    private LongFilter customerId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public StringFilter getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(StringFilter billNumber) {
        this.billNumber = billNumber;
    }

    public DoubleFilter getAmount() {
        return amount;
    }

    public void setAmount(DoubleFilter amount) {
        this.amount = amount;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public LongFilter getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(LongFilter orderLineId) {
        this.orderLineId = orderLineId;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderCriteria that = (OrderCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(billNumber, that.billNumber) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(status, that.status) &&
            Objects.equals(name, that.name) &&
            Objects.equals(orderLineId, that.orderLineId) &&
            Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        date,
        billNumber,
        amount,
        status,
        name,
        orderLineId,
        customerId
        );
    }

    @Override
    public String toString() {
        return "OrderCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (billNumber != null ? "billNumber=" + billNumber + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (orderLineId != null ? "orderLineId=" + orderLineId + ", " : "") +
                (customerId != null ? "customerId=" + customerId + ", " : "") +
            "}";
    }

}
