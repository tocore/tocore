package oasis.test.web;

public class Fruit {

	public Fruit() {
		// TODO Auto-generated constructor stub
	}
	
	private String _id;
	private String name;
	private int price;
	private Taste taste;

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Taste getTaste() {
		return taste;
	}
	public void setTaste(Taste taste) {
		this.taste = taste;
	}

}
