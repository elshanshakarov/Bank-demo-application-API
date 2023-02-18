package az.elsen.bankdemo.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReqCustomer {
    private Long customerId;
    private String name;
    private String surname;
    private String address;
    private Date dob;
    private String phone;
    private String cif;
    private String pin;
    private String seria;

}
