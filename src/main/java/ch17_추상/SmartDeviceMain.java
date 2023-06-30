package ch17_추상;

public class SmartDeviceMain {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone("아이폰", 6.1, "010-9606-3110");
        Pad pad = new Pad("아이패드3",10.1,false);

        System.out.println(phone);
        System.out.println(pad);

        phone.connectedWifi();
        pad.connectedWifi();


    }
}
