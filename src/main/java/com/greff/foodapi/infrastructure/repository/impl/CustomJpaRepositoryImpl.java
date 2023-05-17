package com.greff.foodapi.infrastructure.repository.impl;

import com.greff.foodapi.domain.repository.CustomJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

//The class extends SimpleJpaRepository<T, I>, which is the default implementation provided by Spring Data JPA for the CRUD repository operations
//that way mine repositories can use CRUD operations as normal, need to inherit and don't need to 'create the wheel again'
//implements methods of our custom jpa repository interface, here is where can be modified and such
public class CustomJpaRepositoryImpl<T, I> extends SimpleJpaRepository<T, I> implements CustomJpaRepository<T, I> {

    private final EntityManager manager;

    //It receives the JpaEntityInformation object and the EntityManager as parameters,
    //which are required by the superclass constructor (SimpleJpaRepository) to initialize the repository.
    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager; //The EntityManager is also stored in a local variable named manager for later use.
    }

    @Override
    public void detach(T entity) {
        manager.detach(entity);
    }
}
//By creating this custom implementation class, you are able to extend the functionality provided by SimpleJpaRepository
//and add your own custom methods or overrides as needed.
//In this case, have added the detach method to detach entities from the persistence context.