package com.greff.foodapi.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @CreationTimestamp
    @Column(name = "register_date", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime registerDate;

    @ManyToMany
    @JoinTable(name = "tb_users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups = new ArrayList<>();

    public boolean currentPasswordIsSimilarTo(String password) { //if it's equal a = a, return true, if not false
        return getPassword().equals(password);
    }

//    public boolean currentPasswordIsNotSimilarTo(String password) { if method at line 38 is true, return false, if it's false, return true
//        return !currentPasswordIsSimilarTo(password); //i'm doing by myself, other way
//    }


}