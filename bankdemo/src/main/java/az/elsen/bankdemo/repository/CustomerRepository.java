package az.elsen.bankdemo.repository;

import az.elsen.bankdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//arxada repository bean yaradir. Eslinde yazmaga ehtiyac yoxdur. Cunki CustomerRepository-in extend etdiyi classda bu annotation cagrilib
//@Component //bunu istifade etdikde Service yaxud Repository annotationun isletmeye ehtiyac olmur
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByActive(Integer active);

    Customer findCustomerByIdAndActive(Long id, Integer active);
}
