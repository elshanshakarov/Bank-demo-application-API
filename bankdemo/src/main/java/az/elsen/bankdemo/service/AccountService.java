package az.elsen.bankdemo.service;

import az.elsen.bankdemo.dto.request.ReqAccount;
import az.elsen.bankdemo.dto.response.RespAccount;
import az.elsen.bankdemo.dto.response.Response;

import java.util.List;

public interface AccountService {
    Response<List<RespAccount>> getAccountListByCustomerId(Long customerId);

    Response createAccount(ReqAccount reqAccount);

    Response<List<RespAccount>> getAllAccountList();
}
