package ch12_상속;

public class Computer extends Product{
    private String type;

    public Computer() {
        super();
    }

    //상속 관계에서의 AllArgsConstructor
    public Computer(String model, int price, String type) {
        super(model, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
