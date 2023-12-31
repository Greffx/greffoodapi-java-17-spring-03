package com.greff.foodapi.domain.model;

import com.greff.foodapi.domain.enums.OrderStatus;
import com.greff.foodapi.domain.usecase.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_orders")
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private String uuid;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "delivery_tax", nullable = false)
    private BigDecimal deliveryTax;

    @Column(nullable = false)
    private BigDecimal total;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime creationDate;

    @Column(name = "confirmed_date", columnDefinition = "datetime")
    private OffsetDateTime confirmedDate;

    @Column(name = "canceled_date", columnDefinition = "datetime")
    private OffsetDateTime canceledDate;

    @Column(name = "delivered_date", columnDefinition = "datetime")
    private OffsetDateTime deliveredDate;

    //when an order is created, will always start with "CREATED" enum
    //help to convert String to enum type
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY) //using lazy fetch because we don't always need to use this one, so only use on demand
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    //cascade = CascadeType.ALL will only save items when using this one. when trying to save order with items
    //says like "when saving order, if it has items, save it too"
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public void sumOfSubTotalOfAllItems(List<OrderItem> items) {
        BigDecimal sum = new BigDecimal(0);

        for (OrderItem orderItem : items) sum = sum.add(orderItem.getTotalPrice());

        setSubtotal(sum);
    }

    public void sumOrderTotalPrice() {
        setTotal(getSubtotal().add(restaurant.getDeliveryTax()));
    }

    public void alteringStatusToConfirmed() {
        setStatus(OrderStatus.CONFIRMED);
        setConfirmedDate(OffsetDateTime.now());
    }

    public void alteringStatusToDelivered() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveredDate(OffsetDateTime.now());
    }

    public void alteringStatusToCanceled() {
        setStatus(OrderStatus.CANCELED);
        setCanceledDate(OffsetDateTime.now());
    }

    //instead of lombok with annotation @Data doing this method, I will implement
    //it's going to be a private one, so only can change in this class
    private void setStatus(OrderStatus newStatus) {
        if (getStatus().cantAlterTo(newStatus, getStatus())) throw new BusinessException(
                String.format("Status %s from order %d can't be alter to %s Status", getStatus(), getId(), newStatus));

        this.status = newStatus;
    }

    //entity class can have some business methods implemented to use, that makes entity class rich in content,
    //it is ok, can be only used for entity representation too, depends on how you want to use
}
