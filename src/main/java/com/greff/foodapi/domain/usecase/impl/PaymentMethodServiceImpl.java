package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.PaymentMethod;
import com.greff.foodapi.domain.repository.PaymentMethodRepository;
import com.greff.foodapi.domain.usecase.PaymentMethodService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.PaymentMethodNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    @Override
    public PaymentMethod create(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    @Override
    public PaymentMethod update(PaymentMethod paymentMethod) {
        return create(paymentMethod);
    }

    @Override
    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException(id));
    }

    @Override
    public Set<PaymentMethod> findAll() {
        return new HashSet<>(paymentMethodRepository.findAll());
        //This HashSet is an implementation of Set interface
        //Constructs a new set containing
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        findById(id);

        try {
            paymentMethodRepository.deleteById(id);
            paymentMethodRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Payment method", id);
        }

    }
}
