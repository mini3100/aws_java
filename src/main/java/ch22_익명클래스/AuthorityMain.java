package ch22_익명클래스;

public class AuthorityMain {
    public static void main(String[] args) {
        GrantedAuthorities authorities = new GrantedAuthorities() { //GrantedAuthorities 인터페이스는 상속 받지만, 클래스 이름이 x
            @Override
            public String getAutority() {
                System.out.println("권한 출력");
                return "ROLE_USER";
            }
        };
        System.out.println(authorities.getAutority());

        A a = new A();
        System.out.println(a.getAutority());

    }
}

class A implements GrantedAuthorities { //default 동일 패키지 안에서만 사용 가능

    @Override
    public String getAutority() {
        System.out.println("두 번째 권한 출력");
        return "ROLE_ADMIN";
    }
}