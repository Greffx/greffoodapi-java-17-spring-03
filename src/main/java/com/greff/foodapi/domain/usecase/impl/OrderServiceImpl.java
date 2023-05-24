package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.enums.OrderStatus;
import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.model.Product;
import com.greff.foodapi.domain.repository.OrderRepository;
import com.greff.foodapi.domain.usecase.*;
import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import com.greff.foodapi.domain.usecase.exception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final PaymentMethodService paymentMethodService;
    private final ProductService productService;
    private final CityService cityService;
    private final UserService userService;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order", id));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public Order create(Order order) {
        try {
            var restaurant = restaurantService.findById(order.getRestaurant().getId());
            var paymentMethod = paymentMethodService.findById(order.getPaymentMethod().getId());
            var city = cityService.findById(order.getAddress().getCity().getId());
            var user = userService.findById(1L);

            order.setRestaurant(restaurant);
            order.setDeliveryTax(restaurant.getDeliveryTax());
            order.setPaymentMethod(paymentMethod);
            order.setStatus(OrderStatus.CREATED);
            order.setUser(user);
            order.getAddress().setCity(city);

            order.getItems().forEach(eachOrderItem -> {
                Product product = productService.findProductThroughRestaurant(
                        order.getRestaurant().getId(), eachOrderItem.getProduct().getId());

                eachOrderItem.setOrder(order);
                eachOrderItem.setProduct(product);
                eachOrderItem.setUnityPrice(eachOrderItem.getProduct().getPrice());

                var totalPerOrderItem = eachOrderItem.calculatingSubTotal(
                        eachOrderItem.getProduct().getPrice(), eachOrderItem.getQuantity());

                eachOrderItem.setTotalPrice(totalPerOrderItem);
            });

            var subTotalOfAllItems = order.sumOfSubTotalOfAllItems(order.getItems());
            var totalPrice = order.sumOrderTotalPrice(subTotalOfAllItems, restaurant.getDeliveryTax());

            order.setSubtotal(subTotalOfAllItems);
            order.setTotal(totalPrice);

            return orderRepository.save(order);

        } catch (NotFoundObjectException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
