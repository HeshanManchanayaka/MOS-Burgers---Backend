package edu.icet.repository;

import edu.icet.dto.Customer;
import edu.icet.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {


    Customer findByContactNumber(String tp);
}
