package Test230706;

class Factory {
	private static Factory instance;
	
	private String factoryName;
	
	private Factory() {
		factoryName = "SAMSUNG";
	}
	public static Factory getInstance() {
		if(instance == null)
			instance = new Factory();
		return instance;
	}
	public String getFactoryName() {
		return factoryName;
	}
}
public class Main5 {
	public static void main(String[] args) {
		Factory factory = Factory.getInstance();
		System.out.println(factory.getFactoryName());
	}
	
}
