package com.greff.foodapi.domain.model;

import com.greff.foodapi.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(name = "subtotal_per_item", nullable = false)
    private BigDecimal subtotalPerItem;

    @Column(name = "delivery_tax", nullable = false)
    private BigDecimal deliveryTax;

    @Column(nullable = false)
    private BigDecimal total;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "confirmed_date", columnDefinition = "datetime")
    private LocalDateTime confirmedDate;

    @UpdateTimestamp
    @Column(name = "canceled_date", columnDefinition = "datetime")
    private LocalDateTime canceledDate;

    @UpdateTimestamp
    @Column(name = "delivered_date", columnDefinition = "datetime")
    private LocalDateTime deliveredDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
