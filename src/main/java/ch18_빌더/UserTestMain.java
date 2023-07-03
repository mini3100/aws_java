package ch18_빌더;

public class UserTestMain {
    public static void main(String[] args) {
        (new UserTest()).test();
        new UserTest().test();
        new UserTest.UserTestTest().testTest();
    }
}
