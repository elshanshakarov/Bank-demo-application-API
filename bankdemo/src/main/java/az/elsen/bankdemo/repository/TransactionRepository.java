package az.elsen.bankdemo.repository;

import az.elsen.bankdemo.Enums.EnumAvailableStatus;
import az.elsen.bankdemo.entity.Account;
import az.elsen.bankdemo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByDtAccountAndActive(Account dtAccount, Integer active);
}
