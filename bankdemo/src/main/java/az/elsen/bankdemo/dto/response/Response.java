package az.elsen.bankdemo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response<T> {
    @JsonProperty(value = "response")//Json-da t deyiseni t kimi yox response kimi gorsenecek
    private T t;
    private RespStatus status;
}
