package springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdemo.dao.CustomerDAO;
import springdemo.entity.Customer;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    // need to inject customer dao
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int theId) {
         return customerDAO.getCustomer(theId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {

        return customerDAO.searchCustomers(theSearchName);
    }
}
