package az.elsen.bankdemo.repository;

import az.elsen.bankdemo.Enums.EnumAvailableStatus;
import az.elsen.bankdemo.entity.Account;
import az.elsen.bankdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByActive(Integer active);

    List<Account> findAllByCustomerAndActive(Customer customer, Integer active);

    Account findAccountByIdAndActive(Long id, Integer active);
}
