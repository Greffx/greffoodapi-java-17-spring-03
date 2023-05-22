package com.greff.foodapi.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Group> groups = new HashSet<>();

    public boolean currentPasswordIsSimilarTo(String password) { //if it's equal a = a, return true, if not false
        return getPassword().equals(password);
    }

    public void disassociateGroup(Group group) {
        getGroups().remove(group);
    }

    public void associateGroup(Group group) {
        getGroups().add(group);
    }

//    public boolean currentPasswordIsNotSimilarTo(String password) { if method at line 38 is true, return false, if it's false, return true
//        return !currentPasswordIsSimilarTo(password); //i'm doing by myself, other way
//    }


}