package ch09_접근지정자;

public class Student2 {
    private String name;
    private int age;
    //데이터를 메소드를 통해 넣어야 됨.

    private void test(){    //클래스 내에서만 쓰는 메소드
        System.out.println("테스트 메소드 호출");
    }

    //Setter
    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    //Getter
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
