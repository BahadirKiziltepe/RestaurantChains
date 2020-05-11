package chains.restaurant.application.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import chains.restaurant.application.model.Item;
import chains.restaurant.application.model.Order;
import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.model.User;
import chains.restaurant.application.repository.ItemRepository;
import chains.restaurant.application.repository.OrderRepository;
import chains.restaurant.application.repository.RestaurantRepository;
import chains.restaurant.application.repository.UserRepository;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/admin/view_users", method = RequestMethod.GET)
	public ModelAndView viewUsers() {
		ModelAndView mav = new ModelAndView("viewUsersForAdmin");
		Iterable<User> userList = userRepository.findAll();
		mav.addObject("userList", userList);
		return mav;
	}

	@RequestMapping(value = "/admin/view_user", method = RequestMethod.GET)
	public ModelAndView viewUser(@RequestParam Long id) {
		ModelAndView mav = new ModelAndView("profileViewAdmin");
		mav.addObject("user", userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = "/admin/edit_user", method = RequestMethod.GET)
	public ModelAndView editUser(@RequestParam Long id) {
		ModelAndView mav = new ModelAndView("editProfileAdmin");
		mav.addObject("user", userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = { "/admin/edit_profile_username" }, method = RequestMethod.GET)
	public ModelAndView editProfileUsername(@RequestParam Long id, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfileAdmin");
		userRepository.findById(id).get().setUsername(newName);
		userRepository.saveAndFlush(userRepository.findById(id).get());
		mav.addObject("user", userRepository.findById(id).get());
		getItemListForUser(mav, userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = { "/admin/edit_profile_password" }, method = RequestMethod.GET)
	public ModelAndView editProfilePassword(@RequestParam Long id, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfileAdmin");
		userRepository.findById(id).get().setPassword(bCryptPasswordEncoder.encode(newName));
		userRepository.saveAndFlush(userRepository.findById(id).get());
		mav.addObject("user", userRepository.findById(id).get());
		getItemListForUser(mav, userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = { "/admin/edit_profile_name" }, method = RequestMethod.GET)
	public ModelAndView editProfileName(@RequestParam Long id, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfileAdmin");
		userRepository.findById(id).get().setName(newName);
		userRepository.saveAndFlush(userRepository.findById(id).get());
		mav.addObject("user", userRepository.findById(id).get());
		getItemListForUser(mav, userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = { "/admin/edit_profile_address" }, method = RequestMethod.GET)
	public ModelAndView editProfileAddress(@RequestParam Long id, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfileAdmin");
		userRepository.findById(id).get().setAddress(newName);
		userRepository.saveAndFlush(userRepository.findById(id).get());
		mav.addObject("user", userRepository.findById(id).get());
		getItemListForUser(mav, userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = { "/admin/edit_profile_creditcard" }, method = RequestMethod.GET)
	public ModelAndView editProfileCreditCart(@RequestParam Long id, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfileAdmin");
		userRepository.findById(id).get().setCreditCard(newName);
		userRepository.saveAndFlush(userRepository.findById(id).get());
		mav.addObject("user", userRepository.findById(id).get());
		getItemListForUser(mav, userRepository.findById(id).get());
		return mav;
	}

	@RequestMapping(value = "/admin/view_user_orders", method = RequestMethod.GET)
	public ModelAndView viewUserOrders(@RequestParam Long id) {
		ModelAndView mav = new ModelAndView("ordersAdmin");
		ArrayList<Order> orders = new ArrayList<>();
		for (Long order : userRepository.findById(id).get().getOrders()) {
			orders.add(orderRepository.findById(order).get());
		}
		Iterable<Order> orderList = orders;
		mav.addObject("orderList", orderList);
		return mav;
	}

	@RequestMapping(value = "/admin/view_user_order", method = RequestMethod.GET)
	public ModelAndView viewUserOrder(@RequestParam Long id) {
		ModelAndView mav = new ModelAndView("viewOrderAdmin");
		ArrayList<Item> orders = new ArrayList<>();
		for (Long order : orderRepository.findById(id).get().getOrderedItems()) {
			orders.add(itemRepository.findById(order).get());
		}
		Iterable<Item> orderUser = orders;
		mav.addObject("id", id);
		mav.addObject("total", orderRepository.findById(id).get().getTotal());
		mav.addObject("orderUser", orderUser);
		return mav;
	}

	public void getItemListForUser(ModelAndView mav, User user) {
		double total = 0;
		ArrayList<Item> items = new ArrayList<Item>();
		for (Long id : user.getShoppingCart()) {
			if (itemRepository.existsById(id)) {
				items.add(itemRepository.findById(id).get());
				total += itemRepository.findById(id).get().getPrice();
			} else {
				user.getShoppingCart().remove(id);
			}
		}
		Iterable<Item> itemListUser = items;
		mav.addObject("total", total);
		mav.addObject("itemListUser", itemListUser);
	}

}
