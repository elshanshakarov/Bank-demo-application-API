package az.elsen.bankdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespTransaction {
    private Long id;
    private String receiptNo;
    private RespAccount dtAccount;
    private String crAccount;
    private Double amount;
}
