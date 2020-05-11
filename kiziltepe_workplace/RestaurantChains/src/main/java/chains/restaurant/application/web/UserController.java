package chains.restaurant.application.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import chains.restaurant.application.model.Item;
import chains.restaurant.application.model.Order;
import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.model.Role;
import chains.restaurant.application.model.User;
import chains.restaurant.application.repository.ItemRepository;
import chains.restaurant.application.repository.OrderRepository;
import chains.restaurant.application.repository.RestaurantRepository;
import chains.restaurant.application.repository.UserRepository;
import chains.restaurant.application.service.UserService;
import chains.restaurant.application.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/registrationOwner", method = RequestMethod.GET)
	public String registrationOwner(Model model) {
		model.addAttribute("userForm", new User());

		return "registrationOwner";
	}

	@RequestMapping(value = "/registrationOwner", method = RequestMethod.POST)
	public String registrationOwner(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
			Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registrationOwner";
		}

		userService.save(userForm);

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "", "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView("welcome");
		Iterable<Restaurant> restaurantList = restaurantRepository.findAll();
		mav.addObject("restaurantList", restaurantList);
		return mav;
	}

	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public ModelAndView profile(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("profile");
		User user = userRepository.findByUsername(name);
		mav.addObject("user", user);
		getItemListForUser(mav, user);
		return mav;
	}

	@RequestMapping(value = { "/edit_profile" }, method = RequestMethod.GET)
	public ModelAndView editProfile(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("editProfile");
		User user = userRepository.findByUsername(name);
		mav.addObject("user", user);
		getItemListForUser(mav, user);
		return mav;
	}

	@RequestMapping(value = { "/edit_profile_name" }, method = RequestMethod.GET)
	public ModelAndView editProfileName(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfile");
		User user = userRepository.findByUsername(name);
		user.setName(newName);
		userRepository.saveAndFlush(user);
		mav.addObject("user", user);
		getItemListForUser(mav, user);
		return mav;
	}

	@RequestMapping(value = { "/edit_profile_address" }, method = RequestMethod.GET)
	public ModelAndView editProfileAddress(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfile");
		User user = userRepository.findByUsername(name);
		user.setAddress(newName);
		userRepository.saveAndFlush(user);
		mav.addObject("user", user);
		getItemListForUser(mav, user);
		return mav;
	}

	@RequestMapping(value = { "/edit_profile_creditcard" }, method = RequestMethod.GET)
	public ModelAndView editProfileCreditCart(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editProfile");
		User user = userRepository.findByUsername(name);
		user.setCreditCard(newName);
		userRepository.saveAndFlush(user);
		mav.addObject("user", user);
		getItemListForUser(mav, user);
		return mav;
	}

	@RequestMapping(value = { "/shoppingcart" }, method = RequestMethod.GET)
	public ModelAndView shoppingCart(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("shoppingcart");
		User user = userRepository.findByUsername(name);
		mav.addObject("user", user);
		getItemListForUser(mav, user);
		return mav;
	}

	@RequestMapping(value = { "/checkout" }, method = RequestMethod.GET)
	public ModelAndView shoppingCartCheckout(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("checkout");
		User user = userRepository.findByUsername(name);
		double check = 0;
		for(Long itemId : user.getShoppingCart()) {
			check += itemRepository.findById(itemId).get().getPrice();
		}
		if(check == 0) {
			mav = new ModelAndView("shoppingcart");
			mav.addObject("total", check);
			return mav;
		}
		Order userOrder = new Order();
		orderRepository.save(userOrder);
		userOrder.setOrderedItems(new HashSet<>());
		double total = 0;
		for(Long itemId : user.getShoppingCart()) {
			userOrder.getOrderedItems().add(itemId);
			total += itemRepository.findById(itemId).get().getPrice();
		}
		userOrder.setTotal(total);
		userOrder.setUser(user.getId());
		orderRepository.saveAndFlush(userOrder);
		checkRestaurants(user.getShoppingCart(), userOrder);
		getItemListForUser(mav, user);
		user.getShoppingCart().clear();
		user.getOrders().add(userOrder.getId());
		userRepository.saveAndFlush(user);
		mav.addObject("user", user);
		mav.addObject("id", userOrder.getId());
		return mav;
	}
	
	@RequestMapping(value = { "/orders" }, method = RequestMethod.GET)
	public ModelAndView orders(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("orders");
		User user = userRepository.findByUsername(name);
		mav.addObject("user", user);
		getOrderList(mav, user);
		return mav;
	}
	
	@RequestMapping(value = { "/view_order" }, method = RequestMethod.GET)
	public ModelAndView viewOrder(@RequestParam Long id) {
		ModelAndView mav = new ModelAndView("viewOrder");
		mav.addObject("id", id);
		getOrder(mav, id);
		return mav;
	}

	@RequestMapping(value = { "/view_restaurant" }, method = RequestMethod.GET)
	public ModelAndView viewRestaurant(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("viewRestaurantForUser");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		getItemListForRestaurant(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = { "/view_restaurant/add_item" }, method = RequestMethod.GET)
	public ModelAndView additem(@RequestParam String name, @RequestParam String user, @RequestParam Long id) {
		ModelAndView mav = new ModelAndView("viewRestaurantForUser");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		userRepository.findByUsername(user).getShoppingCart().add(id);
		userRepository.saveAndFlush(userRepository.findByUsername(user));
		getItemListForRestaurant(mav, restaurant);
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
	
	public void getOrderList(ModelAndView mav, User user) {
		ArrayList<Order> orders = new ArrayList<>();
		for(Long id : user.getOrders()) {
			orders.add(orderRepository.findById(id).get());
		}
		Iterable<Order> orderList = orders;
		mav.addObject("orderList", orderList);
	}
	
	public void getOrder(ModelAndView mav, Long id) {
		ArrayList<Item> items = new ArrayList<>();
		for(Long itemId : orderRepository.findById(id).get().getOrderedItems()) {
			items.add(itemRepository.findById(itemId).get());
		}
		Iterable<Item> orderUser = items;
		mav.addObject("total", orderRepository.findById(id).get().getTotal());
		mav.addObject("orderUser", orderUser);
	}

	public void getItemListForRestaurant(ModelAndView mav, Restaurant restaurant) {
		ArrayList<Item> items = new ArrayList<Item>();
		for (Long id : restaurant.getMenu()) {
			if (itemRepository.existsById(id)) {
				items.add(itemRepository.findById(id).get());
			} else {
				restaurant.getMenu().remove(id);
			}
		}
		Iterable<Item> itemListRestaurant = items;
		mav.addObject("itemListRestaurant", itemListRestaurant);
	}
	
	public void checkRestaurants(HashSet<Long> shoppingCart, Order order) {
		for(Long id : shoppingCart) {
			for(Restaurant restaurant : restaurantRepository.findAll()) {
				if(restaurant.getMenu().contains(id)) {
					restaurant.getOrders().add(order.getId());
					restaurantRepository.saveAndFlush(restaurant);
				}
			}
		}
	}
}
