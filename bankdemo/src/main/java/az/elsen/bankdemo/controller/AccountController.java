package az.elsen.bankdemo.controller;

import az.elsen.bankdemo.dto.request.ReqAccount;
import az.elsen.bankdemo.dto.response.RespAccount;
import az.elsen.bankdemo.dto.response.Response;
import az.elsen.bankdemo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/GetAllAccountList")
    public Response<List<RespAccount>> getAllAccountList(){
        return accountService.getAllAccountList();
    }

    @GetMapping("/GetAcoountList/{customerId}")
    public Response<List<RespAccount>> getAccountListByCustomerId(@PathVariable Long customerId) {
        return accountService.getAccountListByCustomerId(customerId);
    }

    @PostMapping("/CreateAccount")
    public Response createAccount(@RequestBody ReqAccount reqAccount){
        return accountService.createAccount(reqAccount);
    }
}
