package com.expeditors.ecommerce.repository;

import com.expeditors.ecommerce.entities.Customer;
import com.expeditors.ecommerce.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String username);

    Customer findByRole(Role role);

    boolean existsByEmail(String email);
    boolean existsByGscId(String gscId);
}
