package edu.icet.service;

import edu.icet.dto.Customer;

import java.util.List;

public interface CustomerService {
    Customer searchByContactNumber(String string);

     void deleteCustomerById(int customerId) ;

    void addCustomer(Customer customer);

    List<Customer> getAll();
}
