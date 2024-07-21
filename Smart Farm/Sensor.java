/*Author: Hai Dang Luong
*Student No: C3441308
*Date: 19/04/2024
*Description: This is the sensor file which contain private data and public method to
set sensor or return sensor data.
*/
public class Sensor {
	private String type;
	private double price = 0;
	private double weight = 0;
	private int quantity = 0;

	public String getType() {
		return type;
	};

	public double getPrice() {
		return price;
	};

	public double getWeight() {
		return weight;
	};

	public int getQuantity() {
		return quantity;
	};

	public void setType(String t) {
		type = t;
	};

	public void setPrice(double p) {
		price = p;
	};

	public void setWeight(double w) {
		weight = w;
	};

	public void setQuantity(int q) {
		quantity = q;
	};

}
