package az.elsen.bankdemo.service.impl;

import az.elsen.bankdemo.Enums.EnumAvailableStatus;
import az.elsen.bankdemo.dto.request.ReqAccount;
import az.elsen.bankdemo.dto.response.RespAccount;
import az.elsen.bankdemo.dto.response.RespCustomer;
import az.elsen.bankdemo.dto.response.RespStatus;
import az.elsen.bankdemo.dto.response.Response;
import az.elsen.bankdemo.entity.Account;
import az.elsen.bankdemo.entity.Customer;
import az.elsen.bankdemo.exception.BankException;
import az.elsen.bankdemo.exception.ExceptionConstants;
import az.elsen.bankdemo.repository.AccountRepository;
import az.elsen.bankdemo.repository.CustomerRepository;
import az.elsen.bankdemo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Response<List<RespAccount>> getAccountListByCustomerId(Long customerId) {
        Response<List<RespAccount>> response = new Response<>();
        try {
            List<RespAccount> respAccountList = new ArrayList<>();
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer Not Found");
            }
            List<Account> accountList = accountRepository.findAllByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.getValue());
            if (accountList.isEmpty()) {
                throw new BankException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account Not Found");
            }
            for (Account account : accountList) {
                RespAccount respAccount = convert(account);
                respAccountList.add(respAccount);
            }
            response.setT(respAccountList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public Response createAccount(ReqAccount reqAccount) {
        Response response = new Response();
        try {
            Long customerId = reqAccount.getCustomerId();
            String name = reqAccount.getName();
            if (customerId == null || name == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(reqAccount.getCustomerId(), EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer Not Found");
            }
            Account account = Account.builder().
                    name(name).
                    accountNo(reqAccount.getAccountNo()).
                    iban(reqAccount.getIban()).
                    amount(reqAccount.getAmount()).
                    currency(reqAccount.getCurrency()).
                    customer(customer).
                    build();
            accountRepository.save(account);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response<List<RespAccount>> getAllAccountList() {
        Response<List<RespAccount>> response = new Response<>();
        try {
            List<RespAccount> respAccountList = new ArrayList<>();
            List<Account> accountList = accountRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (accountList.isEmpty()) {
                throw new BankException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account Not Found");
            }
            for (Account account : accountList) {
                RespAccount respAccount = convert(account);
                respAccountList.add(respAccount);
            }
            response.setT(respAccountList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    private RespAccount convert(Account account) {
        RespCustomer respCustomer = RespCustomer.builder().
                id(account.getCustomer().getId()).
                name(account.getCustomer().getName()).
                surname(account.getCustomer().getSurname()).
                build();
        RespAccount respAccount = new RespAccount().builder().
                id(account.getId()).
                name(account.getName()).
                accountNo(account.getAccountNo()).
                iban(account.getIban()).
                amount((double) account.getAmount() / 100).
                currency(account.getCurrency()).
                respCustomer(respCustomer).
                build();
        return respAccount;
    }
}
