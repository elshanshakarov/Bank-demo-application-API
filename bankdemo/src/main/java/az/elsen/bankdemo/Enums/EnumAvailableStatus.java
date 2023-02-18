package az.elsen.bankdemo.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumAvailableStatus {
    ACTIVE(1), DEACTIVE(0), DELETE(2);

    private int value;



}
