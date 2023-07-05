package ch22_익명클래스;

import java.util.Arrays;

public class LamdaTest {
    public static void main(String[] args) {
        GrantedAuthorities authorities = () -> {    //authorities는 하나의 객체
            System.out.println("권한 출력");
            return "ROLE_USER";
        };

        System.out.println(authorities.getAutority());

        Integer[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Arrays.asList(test).forEach( num -> System.out.println(num));
    }
}
