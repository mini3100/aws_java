package ch17_추상;

import lombok.ToString;

@ToString(callSuper = true)
public class SmartPhone extends SmartDevice implements Button {

    private String phoneNumber;

    public SmartPhone(String deviceName, double displaySize, String phoneNumber) {
        super(deviceName, displaySize);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void connectedWifi() {
        System.out.println("스마트폰의 와이파이를 연결합니다.");
    }

    @Override
    public void powerOn() {

    }

    @Override
    public void powerOff() {

    }

    @Override
    public void volumeUp() {

    }

    @Override
    public void volumeDown() {

    }
}
