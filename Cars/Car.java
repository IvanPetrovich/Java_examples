package ProjectCars;

public class Car {
	String brand;
	String model;
	int year;
	int price;
	
	public Car(String brand, String model, int year, int price) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.price = price;
	}
	
	public String showInfo() {
		
		return brand + " " + model + "\nГод: " + year + "\nЦена: " + price; 
		
	}
	
}
