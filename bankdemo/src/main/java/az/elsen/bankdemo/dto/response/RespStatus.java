package az.elsen.bankdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespStatus {
    private Integer code;
    private String message;

    private final static Integer SUCCESS_CODE = 1;
    private final static String SUCCESS_MESSAGE = "Success";

    public static RespStatus getSuccessMessage() {
        return new RespStatus(SUCCESS_CODE, SUCCESS_MESSAGE);
    }
}
