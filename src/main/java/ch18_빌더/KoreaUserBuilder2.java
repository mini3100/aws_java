package ch18_빌더;

public class KoreaUserBuilder2 {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;

    public KoreaUser build() {  //KoreaUser 객체를 리턴
        return new KoreaUser(userId, username, password, name, email);
    }

    public KoreaUserBuilder2 userId(int userId) {
        this.userId = userId;
        return this;    //자기 자신을 리턴
    }

    public KoreaUserBuilder2 username(String username) {
        this.username = username;
        return this;
    }

    public KoreaUserBuilder2 password(String password) {
        this.password = password;
        return this;
    }

    public KoreaUserBuilder2 name(String name) {
        this.name = name;
        return this;
    }

    public KoreaUserBuilder2 email(String email) {
        this.email = email;
        return this;
    }
}
