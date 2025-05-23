package edu.icet.controller;

import edu.icet.dto.Customer;
import edu.icet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin

public class CustomerController {

    final CustomerService service;

    @PostMapping("/add")
    public void addCustomer(@RequestBody Customer customer){
        service.addCustomer(customer);
    }

    @GetMapping("/getAll")
    public List<Customer> getAll(){
        return service.getAll();
    }

    @GetMapping("/search-by-contactNumber/{tp}")
    public Customer searchByContact (@PathVariable String tp){
        return  service.searchByContactNumber(tp);
    }

    @DeleteMapping("/delete")
    public void deleteCuatomerById(@RequestParam int customerId){
        service.deleteCustomerById(customerId);
    }
}
