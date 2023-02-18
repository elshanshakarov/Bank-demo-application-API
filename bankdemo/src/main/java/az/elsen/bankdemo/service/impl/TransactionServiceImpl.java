package az.elsen.bankdemo.service.impl;

import az.elsen.bankdemo.Enums.EnumAvailableStatus;
import az.elsen.bankdemo.dto.response.*;
import az.elsen.bankdemo.entity.Account;
import az.elsen.bankdemo.entity.Transaction;
import az.elsen.bankdemo.exception.BankException;
import az.elsen.bankdemo.exception.ExceptionConstants;
import az.elsen.bankdemo.repository.AccountRepository;
import az.elsen.bankdemo.repository.TransactionRepository;
import az.elsen.bankdemo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public Response<List<RespTransaction>> getTransactionListByAccountId(Long accountId) {
        Response<List<RespTransaction>> response = new Response<>();
        try {
            List<RespTransaction> respTransactionList = new ArrayList<>();
            if (accountId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
            }
            Account account = accountRepository.findAccountByIdAndActive(accountId, EnumAvailableStatus.ACTIVE.getValue());
            if (account == null) {
                throw new BankException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account Not Found");
            }
            List<Transaction> transactionList = transactionRepository.findAllByDtAccountAndActive(account, EnumAvailableStatus.ACTIVE.getValue());
            if (transactionList.isEmpty()) {
                throw new BankException(ExceptionConstants.TRANSACTION_NOT_FOUND, "Transaction Not Found");
            }
            for (Transaction transaction : transactionList) {
                RespTransaction respTransaction = convert(transaction);
                respTransactionList.add(respTransaction);
            }
            response.setT(respTransactionList);
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

    private RespTransaction convert(Transaction transaction) {
        RespCustomer respCustomer=RespCustomer.builder()
                .id(transaction.getDtAccount().getCustomer().getId())
                .name(transaction.getDtAccount().getCustomer().getName())
                .surname(transaction.getDtAccount().getCustomer().getSurname())
                .build();
        RespAccount respAccount = RespAccount.builder()
                .id(transaction.getDtAccount().getId())
                .name(transaction.getDtAccount().getName())
                .accountNo(transaction.getDtAccount().getAccountNo())
                .iban(transaction.getDtAccount().getIban())
                .amount(Double.valueOf(transaction.getDtAccount().getAmount())/100)
                .currency(transaction.getDtAccount().getCurrency())
                .respCustomer(respCustomer)
                .build();
        RespTransaction respTransaction = RespTransaction.builder()
                .id(transaction.getId())
                .receiptNo(transaction.getReceiptNo())
                .dtAccount(respAccount)
                .crAccount(transaction.getCrAccount())
                .amount((double) transaction.getAmount() / 100)
                .build();
        return respTransaction;
    }

}
