package chains.restaurant.application.model;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	private Long id;
	private String name;
	private String address;

	private HashSet<Long> menu;
	private HashSet<Long> orders;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public HashSet<Long> getMenu() {
		return menu;
	}

	public void setMenu(HashSet<Long> menu) {
		this.menu = menu;
	}

	public HashSet<Long> getOrders() {
		return orders;
	}

	public void setOrders(HashSet<Long> orders) {
		this.orders = orders;
	}
}
