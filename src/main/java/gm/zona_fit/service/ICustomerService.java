package gm.zona_fit.service;

import gm.zona_fit.model.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> listCustomers();

    public Customer findCustomerById(Integer idCustomer);

    public void saveCustomer(Customer Customer);

    public void deleteCustomer(Customer Customer);

}
