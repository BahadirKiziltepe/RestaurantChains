package chains.restaurant.application.model;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	private Long id;

	private Long user;
	private double total;
	private HashSet<Long> orderedItems;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = Integer.MAX_VALUE)
	public HashSet<Long> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(HashSet<Long> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}	
}
