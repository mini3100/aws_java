package ch17_추상;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
public abstract class SmartDevice { //추상 클래스
    private String deviceName;
    private double displaySize;

    public abstract void connectedWifi();   //추상 메소드
}
