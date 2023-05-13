package service;

import model.Customer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomerService {

    private static CustomerService singletonCustomerService = new CustomerService();
    private Map<String,Customer> mapOfCustomer = new HashMap<String,Customer>();
    private CustomerService(){} //make sure there is only one CustomerService
    public static CustomerService getInstance()
    {
        return singletonCustomerService;
    }

    public void addCustomer(String email,String firstName,String lastName)
    {
        if(!mapOfCustomer.containsKey(email))
        {
            mapOfCustomer.put(email,new Customer(email,firstName,lastName));
        }
        else
        {
            throw new IllegalArgumentException("This Email is registered");
        }
    }

    public Customer getCustomer(String customerEmail)
    {
        return mapOfCustomer.get(customerEmail); //if there are no key in mapOfCustomer, it will return null
    }

    public List<Customer> getAllCustomers()
    {
        List<Customer> customers = new LinkedList<Customer>();
        for(String email :  mapOfCustomer.keySet())
        {
            customers.add(mapOfCustomer.get(email));
        }
        return customers;
    }
}
