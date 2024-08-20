package ezechukwu.service;

import ezechukwu.model.Customer;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerService {
    private final Set<Customer> customers = new HashSet<>();
    private static CustomerService INSTANCE;

    private CustomerService() {
    }
    public static CustomerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }
    public void addCustomer(String email, String firstName, String lastName){
        customers.add(new Customer(firstName,lastName, email));
    }
    public Optional<Customer> getCustomer(String email){
        for(Customer customer : customers){
            if(customer.getEmail().equals(email)){
                return Optional.of(customer);
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers.stream().toList();
    }
}
