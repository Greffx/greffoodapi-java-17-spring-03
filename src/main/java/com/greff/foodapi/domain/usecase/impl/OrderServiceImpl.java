package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.model.Product;
import com.greff.foodapi.domain.repository.OrderRepository;
import com.greff.foodapi.domain.repository.filter.OrderFilter;
import com.greff.foodapi.domain.usecase.*;
import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import com.greff.foodapi.domain.usecase.exception.OrderNotFoundException;
import com.greff.foodapi.domain.usecase.exception.PaymentMethodNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.greff.foodapi.infrastructure.repository.spec.OrderSpecs.usingFilters;

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
    public Order findByUuid(String uuid) {
        return orderRepository.findByUuid(uuid).orElseThrow(() -> new OrderNotFoundException("Order", uuid));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllWithFilters(OrderFilter orderFilter) {
        System.out.println("PASSOU AQUI 1 ");
        return orderRepository.findAll(usingFilters(orderFilter)); //using specification factor method
    }

    @Transactional
    @Override
    public Order create(Order order) {
        var restaurantId = order.getRestaurant().getId();
        var paymentMethodId = order.getPaymentMethod().getId();

        try {
            validatingOrderObjects(order, restaurantId, paymentMethodId);

            validatingOrderItems(order);

            order.sumOfSubTotalOfAllItems(order.getItems());
            order.sumOrderTotalPrice();

            return orderRepository.save(order);

        } catch (PaymentMethodNotFoundException e) {
            throw new BusinessException(String.format(
                    "Restaurant with id %d don't accept Payment with id %d", restaurantId, paymentMethodId));

        } catch (NotFoundObjectException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void alteringOrderStatusToConfirmed(Order order) {
        order.alteringStatusToConfirmed();
    }

    @Transactional
    @Override
    public void alteringOrderStatusToDelivered(Order order) {
        order.alteringStatusToDelivered();
    }

    @Transactional
    @Override
    public void alteringOrderStatusToCanceled(Order order) {
        order.alteringStatusToCanceled();
    }

    private void validatingOrderItems(Order order) {
        order.getItems().forEach(eachOrderItem -> {
            Product product = productService.findProductThroughRestaurant(
                    order.getRestaurant().getId(), eachOrderItem.getProduct().getId());

            eachOrderItem.setOrder(order);
            eachOrderItem.setProduct(product);
            eachOrderItem.setUnityPrice(product.getPrice());

            eachOrderItem.calculatingSubTotal();
        });
    }

    private void validatingOrderObjects(Order order, Long restaurantId, Long paymentMethodId) {
        var restaurant = restaurantService.findById(restaurantId);
        var paymentMethod = paymentMethodService.findById(paymentMethodId);
        var city = cityService.findById(order.getAddress().getCity().getId());
        var user = userService.findById(1L);

        order.setUuid(UUID.randomUUID().toString());
        order.setRestaurant(restaurant);
        order.setDeliveryTax(restaurant.getDeliveryTax());
        order.setPaymentMethod(paymentMethod);
        order.setUser(user);
        order.getAddress().setCity(city);
    }
}
