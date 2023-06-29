package ch09_접근지정자;

import ch09_접근지정자.Student2;

public class StudentMain2 {
    public static void main(String[] args) {
        Student2 s = new Student2();
        s.setName("정가영");
        s.setAge(23);

        System.out.println(s.getName());
        System.out.println(s.getAge());
    }
}
