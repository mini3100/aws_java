package ch16_lombok;

public class SlaveMain {
    public static void main(String[] args) {
        Slave slave = new Slave("정가영",23);
        Slave slave1 = new Slave("정가영");

        System.out.println(slave);

    }

}
