package edu.icet.service.impl;

import edu.icet.dto.Customer;
import edu.icet.entity.CustomerEntity;
import edu.icet.repository.CustomerRepository;
import edu.icet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository repository;
    final ModelMapper mapper;

    @Override
    public Customer searchByContactNumber(String tp) {
        return repository.findByContactNumber(tp);
    }

    @Override
    public void deleteCustomerById(int customerId) {
        repository.deleteById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        repository.save(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customerList = new ArrayList<>();

        List<CustomerEntity> all = repository.findAll();

        all.forEach(CustomerEntity->{
            customerList.add(mapper.map(CustomerEntity, Customer.class));
        });
        return customerList;
    }
}
