package ch22_익명클래스;

import java.util.ArrayList;
import java.util.function.*;

public class TestFunctionMain {
    public static void main(String[] args) {
//        TestFunction01 function01 = () -> {
//            System.out.println("매개변수 x, 리턴 x");
//        };
        // 명령문이 하나인 경우에는 중괄호{} 를 생략할 수 있다.
        // 여기서 세미콜론은 전체 문장의 끝을 의미 (sout의 세미콜론 x)
        TestFunction01 function01 = () -> System.out.println("매개변수 x, 리턴 x");
        function01.test();

//        TestFunction02 function02 = (int num) -> {
//            System.out.println("매개변수 1개, 리턴 x");
//            System.out.println("num: " + num);
//        };
        //매개변수는 타입을 생략할 수 있다.
        //매개변수가 하나인 경우엔 괄호()도 생략 가능하다.
        TestFunction02 function02 = num -> {
            System.out.println("매개변수 1개, 리턴 x");
            System.out.println("num: " + num);
        };
        function02.test(100);

        //매개변수가 없거나 두 개 이상일 경우에는 매개변수의 괄호를 생략할 수 없다.
        TestFunction03 function03 = (age, name) -> {
            System.out.println("매개변수 2개, 리턴 x");
            System.out.println("나이: " + age);
            System.out.println("이름: " + name);
        };
        function03.test(23, "정가영");

//        TestFunction04 function04 = (age, name) -> {
//            System.out.println("매개변수 2개, 리턴 o");
//            return "나이: " + age + ", 이름: " + name;
//        };
        //명령이 한 줄인 경우 중괄호 생략 가능
        //중괄호 생략한 경우 즉시 리턴값이 된다. (중괄호 생략시 return 쓰면 오류)
        TestFunction04 function04 = (age, name) -> "나이: " + age + ", 이름: " + name;
        String result1 = function04.test(23, "정가영");
        System.out.println(result1);

        System.out.println("===========================================");

        //함수형 인터페이스
        //Runnable
        Runnable runnable = () -> {
            System.out.println("매개변수 x, 리턴 x");
        };
        runnable.run();

        //Consumer: 매개변수 1개, 리턴 x
        Consumer<String> consumerStr = name -> {    //매개변수의 타입이 String
            System.out.println(name);
        };
        consumerStr.accept("정가영");

        //BiConsumer: 매개변수 2개, 리턴 x
        BiConsumer<String, Integer> biConsumer = (name, age) -> {
            System.out.println("이름: " + name);
            System.out.println("나이: " + age);
        };
        biConsumer.accept("정가영", 23);

        //Supplier: 매개변수 x, 리턴 o
        Supplier<Integer> supplier = () -> 100;
        System.out.println(supplier.get());

        //Function: 매개변수 o, 리턴 o
        //Integer: 매개변수 자료형, String: 반환 자료형
        Function<Integer, String> function = year -> "생일: " + year;
        System.out.println(function.apply(2001));

        BiFunction<Integer, String, String> biFunction = (year, name) -> name + "의 생일: "+year;
        System.out.println(biFunction.apply(2001, "정가영"));

        //Predicate: 매개변수 o, 리턴 o
        //removeIf(Predicate): 조건 true인 값 삭제
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 8; i++) numbers.add(i);
        System.out.println(numbers);
        numbers.removeIf(num -> num % 2 == 0);
        System.out.println(numbers);

        BiPredicate<Integer, Integer> biPredicate = (num1, num2) -> num1 > num2;
        System.out.println(biPredicate.test(1,2));

        //forEach(Consumer)
        numbers.forEach(num -> System.out.println("출력: " + num));
    }
}
