package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id")int id){
        Customer customer = customerService.getCustomer(id);

        if (customer == null){
            throw new CustomerNotFoundException("Customer not found id: " + id);
        }

        return customer;
    }

    @PostMapping("/customers")
    public Customer saveCustommer(@RequestBody Customer customer){
        customer.setId(0);

        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    public String  deleteCustomer(@PathVariable("id")int id){
        Customer customer = customerService.getCustomer(id);

        if (customer == null){
            throw new CustomerNotFoundException("Customer not found id: " + id);
        }

        customerService.deleteCustomer(id);

        return "Deleted customer id: " + id;
    }
}
