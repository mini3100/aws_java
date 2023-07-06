package ch24_쓰레드;

public class ThreadMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("스레드 이름: " + Thread.currentThread().getName());
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(2000); //Thread 2초동안 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "1번 스레드");

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드 이름: " + Thread.currentThread().getName());
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "2번 스레드");

        Thread thread3 = new Thread(new ThreadTest(), "3번 스레드");  //인터페이스를 상속받은 클래스로 생성

        Thread thread4 = new Thread(new Runnable() {    //익명메소드로 생성
            @Override
            public void run() {
                System.out.println("스레드 이름: " + Thread.currentThread().getName());
                System.out.println("4번 스레드 실행");
            }
        }, "4번 스레드");

        thread1.start();
//        try {
//            thread1.join(); //thread1의 동작이 끝날 때까지 나머지 thread 대기 상태
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread2.start();    //순서대로 동작하지 않는다.
//        thread3.start();
//        thread4.start();

    }
}
