package chains.restaurant.application.web;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import chains.restaurant.application.model.Item;
import chains.restaurant.application.model.Order;
import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.repository.ItemRepository;
import chains.restaurant.application.repository.OrderRepository;
import chains.restaurant.application.repository.RestaurantRepository;
import chains.restaurant.application.repository.UserRepository;

@Controller
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping(value = "/admin/view_restaurants", method = RequestMethod.GET)
	public ModelAndView viewRestaurants() {
		ModelAndView mav = new ModelAndView("viewRestaurantsForAdmin");
		Iterable<Restaurant> restaurantList = restaurantRepository.findAll();
		mav.addObject("restaurantList", restaurantList);
		return mav;
	}

	@RequestMapping(value = "/owner/view_restaurant", method = RequestMethod.GET)
	public ModelAndView viewRestaurant(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("viewRestaurantForAdminAndOwner");
		String restaurantName = userRepository.findByUsername(name).getMyRestaurant().getName();
		Restaurant restaurant = restaurantRepository.findByName(restaurantName);
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/admin/view_restaurant", method = RequestMethod.GET)
	public ModelAndView viewRestaurantAdmin(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("viewRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant", method = RequestMethod.GET)
	public ModelAndView editRestaurant(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/admin/edit_restaurant", method = RequestMethod.GET)
	public ModelAndView editRestaurantAdmin(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_name", method = RequestMethod.GET)
	public ModelAndView editRestaurantName(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (newName != null) {
			restaurant.setName(newName);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_address", method = RequestMethod.GET)
	public ModelAndView editRestaurantAddress(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (newName != null) {
			restaurant.setAddress(newName);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_edit_item_name", method = RequestMethod.GET)
	public ModelAndView editItemName(@RequestParam String name, @RequestParam Long id, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		itemRepository.findById(id).get().setName(newName);
		itemRepository.saveAndFlush(itemRepository.findById(id).get());
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_edit_item_description", method = RequestMethod.GET)
	public ModelAndView editItemDescription(@RequestParam String name, @RequestParam Long id,
			@RequestParam String newName) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		itemRepository.findById(id).get().setDescription(newName);
		itemRepository.saveAndFlush(itemRepository.findById(id).get());
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_edit_item_price", method = RequestMethod.GET)
	public ModelAndView editItemPrice(@RequestParam String name, @RequestParam Long id, @RequestParam double newPrice) {
		System.out.println(newPrice);
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		itemRepository.findById(id).get().setPrice(newPrice);
		;
		itemRepository.saveAndFlush(itemRepository.findById(id).get());
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_add_item", method = RequestMethod.GET)
	public ModelAndView addRestaurantItem(@RequestParam String restaurantName, Item item) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(restaurantName);
		itemRepository.saveAndFlush(item);
		restaurant.getMenu().add(item.getId());
		restaurantRepository.saveAndFlush(restaurant);
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_remove_item", method = RequestMethod.GET)
	public ModelAndView removeRestaurantItem(@RequestParam String restaurantName, Long id) {
		ModelAndView mav = new ModelAndView("editRestaurantForAdminAndOwner");
		Restaurant restaurant = restaurantRepository.findByName(restaurantName);
		itemRepository.deleteById(id);
		restaurant.getMenu().remove(id);
		restaurantRepository.saveAndFlush(restaurant);
		mav.addObject("restaurant", restaurant);
		getItemList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/orders", method = RequestMethod.GET)
	public ModelAndView orders(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("ordersOwner");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		orderList(mav, restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/view_order", method = RequestMethod.GET)
	public ModelAndView viewOrder(@RequestParam String name, @RequestParam Long id) {
		ModelAndView mav = new ModelAndView("viewOrderOwner");
		mav.addObject("id", id);
		getOrder(mav, id, name);
		return mav;
	}

	public void getItemList(ModelAndView mav, Restaurant restaurant) {
		ArrayList<Item> items = new ArrayList<Item>();
		for (Long id : restaurant.getMenu()) {
			if (itemRepository.existsById(id)) {
				items.add(itemRepository.findById(id).get());
			} else {
				restaurant.getMenu().remove(id);
			}
		}
		Iterable<Item> itemList = items;
		mav.addObject("itemList", itemList);
	}

	public void orderList(ModelAndView mav, Restaurant restaurant) {
		ArrayList<Order> orders = new ArrayList<>();
		for (Long id : restaurant.getOrders()) {
			orders.add(orderRepository.findById(id).get());
		}
		Iterable<Order> orderList = orders;
		mav.addObject("orderList", orderList);
	}

	public void getOrder(ModelAndView mav, Long id, String name) {
		double total = 0;
		ArrayList<Item> items = new ArrayList<>();
		for (Long itemId : orderRepository.findById(id).get().getOrderedItems()) {
			if (restaurantRepository.findByName(name).getMenu().contains(itemId)) {
				items.add(itemRepository.findById(itemId).get());
				total += itemRepository.findById(itemId).get().getPrice();
			}
		}
		Iterable<Item> orderRestaurant = items;
		mav.addObject("total", total);
		mav.addObject("orderRestaurant", orderRestaurant);
	}
}