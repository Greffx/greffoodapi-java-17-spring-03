package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.mapper.PaymentMethodMapper;
import com.greff.foodapi.domain.model.PaymentMethod;
import com.greff.foodapi.domain.repository.PaymentMethodRepository;
import com.greff.foodapi.domain.usecase.PaymentMethodService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.PaymentMethodNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

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
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
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
