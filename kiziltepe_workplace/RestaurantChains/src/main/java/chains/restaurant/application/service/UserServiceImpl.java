package chains.restaurant.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.model.Role;
import chains.restaurant.application.model.User;
import chains.restaurant.application.repository.RestaurantRepository;
import chains.restaurant.application.repository.RoleRepository;
import chains.restaurant.application.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>());
		if(userRepository.count() == 0) {
			for(Role role : roleRepository.findAll())
			user.getRoles().add(role);
		}
		user.getRoles().add(roleRepository.findById(9));
		user.setShoppingCart(new HashSet<>());
		user.setOrders(new HashSet<>());
		if(user.getIsOwner()) {
			user.getRoles().add(roleRepository.findById(1));
			Restaurant restaurant = new Restaurant();
			restaurantRepository.save(restaurant);
			restaurant.setName(Long.toString(restaurant.getId()));
			restaurant.setMenu(new HashSet<>());
			restaurant.setOrders(new HashSet<>());
			restaurantRepository.saveAndFlush(restaurant);
			user.setMyRestaurant(restaurant);
		}
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
