package az.elsen.bankdemo.controller;

import az.elsen.bankdemo.dto.response.RespTransaction;
import az.elsen.bankdemo.dto.response.Response;
import az.elsen.bankdemo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/GetTransactionList/{accountId}")
    public Response<List<RespTransaction>> getTransactionListByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionListByAccountId(accountId);
    }
}


