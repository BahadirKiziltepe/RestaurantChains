package chains.restaurant.application.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
	private Long id;
	private String username;
	private String password;
	private String passwordConfirm;
	private String name;
	private String creditCard;
	private String address;
	private boolean isOwner;
	
	@ManyToMany
	@JoinTable(name = "user_role")
	private Set<Role> roles;
	private HashSet<Long> shoppingCart;
	
	private Restaurant myRestaurant;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public HashSet<Long> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(HashSet<Long> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}	

	@ManyToOne(targetEntity = Restaurant.class)
	public Restaurant getMyRestaurant() {
		return myRestaurant;
	}

	public void setMyRestaurant(Restaurant myRestaurant) {
		this.myRestaurant = myRestaurant;
	}

	public boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
