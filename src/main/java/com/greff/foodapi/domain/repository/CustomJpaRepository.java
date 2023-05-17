package com.greff.foodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
//@NoRepositoryBean annotation is used to indicate that a particular interface should not
//be instantiated as a Spring bean for repository implementation
public interface CustomJpaRepository<T, I> extends JpaRepository<T, I> { //interface to be implemented

    //By having CustomJpaRepository extend JpaRepository<T, I>, inherit all the CRUD methods provided by JpaRepository
    //This allows to use common operations such as save, findById, delete, etc.
    //better to inherit in here than EntityRepository interface, like restaurant or other, just extends CustomJpaRepository class
    //and can create methods to implement in repository impl class

    void detach(T entity); //method to cancel JPA to manage an instance
}
