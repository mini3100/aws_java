package ch14_싱글톤;

import java.time.LocalDate;
import java.util.Date;

public class Samsung {
    private static Samsung instance;

    private String companyName;
    private int autoIncrementSerialNumber = LocalDate.now().getYear() * 10000;

    private Samsung() {
        companyName = "SAMSUNG";
    }

    public static Samsung getInstance() {
        if (instance == null)   //최초의 한 번만 null
            instance = new Samsung();
        return instance;
    }

    public int getAutoIncrementSerialNumber() {
        return autoIncrementSerialNumber;
    }

    public void setAutoIncrementSerialNumber(int autoIncrementSerialNumber) {
        this.autoIncrementSerialNumber = autoIncrementSerialNumber;
    }

    public String getCompanyName() {
        return companyName;
    }


}
