package az.elsen.bankdemo.dto.request;

import az.elsen.bankdemo.dto.response.RespCustomer;
import lombok.Data;

@Data
public class ReqAccount {
    private Long id;
    private String name;
    private String accountNo;
    private String iban;
    private Integer amount;
    private String currency;
    private Long customerId;
}
