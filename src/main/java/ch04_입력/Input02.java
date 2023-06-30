package ch04_입력;

import java.util.Scanner;

public class Input02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name, phone, address, gender;
        int age;

        System.out.print("이름 : ");
        name = scanner.next();
        System.out.print("나이 : ");
        age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("연락처 : ");
        phone = scanner.next();
        System.out.print("주소 : ");
        address = scanner.nextLine();
        System.out.print("성별 : ");
        gender = scanner.next();

        System.out.println();

        System.out.println("고객님의 이름은 " + name + "이고 나이는 " + age + "세 입니다.");
        System.out.println("연락처는 " + phone + "이며 주소는 " + address + " 입니다.");
        System.out.println("성별은 " + gender + "입니다.");
    }
}
