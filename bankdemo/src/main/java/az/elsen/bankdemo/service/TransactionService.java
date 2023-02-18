package az.elsen.bankdemo.service;

import az.elsen.bankdemo.dto.response.RespTransaction;
import az.elsen.bankdemo.dto.response.Response;

import java.util.List;

public interface TransactionService {
    Response<List<RespTransaction>> getTransactionListByAccountId(Long accountId);
}
