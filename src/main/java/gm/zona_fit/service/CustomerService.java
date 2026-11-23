package gm.zona_fit.service;

import gm.zona_fit.model.Customer;
import gm.zona_fit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer findCustomerById(Integer idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElse(null);
        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
