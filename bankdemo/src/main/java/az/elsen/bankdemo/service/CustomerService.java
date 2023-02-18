package az.elsen.bankdemo.service;

import az.elsen.bankdemo.dto.request.ReqCustomer;
import az.elsen.bankdemo.dto.response.RespCustomer;
import az.elsen.bankdemo.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(Long customerId);
}
