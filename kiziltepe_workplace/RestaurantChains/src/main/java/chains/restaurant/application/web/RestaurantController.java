package chains.restaurant.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import chains.restaurant.application.model.Item;
import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.repository.ItemRepository;
import chains.restaurant.application.repository.RestaurantRepository;

@Controller
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ItemRepository itemRepository;

	@RequestMapping(value = "/admin/view_restaurants", method = RequestMethod.GET)
	public ModelAndView viewRestaurants() {
		ModelAndView mav = new ModelAndView("viewRestaurants");
		Iterable<Restaurant> restaurantList = restaurantRepository.findAll();
		mav.addObject("restaurantList", restaurantList);
		return mav;
	}

	@RequestMapping(value = "/owner/view_restaurant", method = RequestMethod.GET)
	public ModelAndView viewRestaurant(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		mav.addObject("restaurant", restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_name", method = RequestMethod.GET)
	public ModelAndView editRestaurantName(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (newName != null) {
			restaurant.setName(newName);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_address", method = RequestMethod.GET)
	public ModelAndView editRestaurantAddress(@RequestParam String name, @RequestParam String newName) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (newName != null) {
			restaurant.setAddress(newName);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_edit_item_name", method = RequestMethod.GET)
	public ModelAndView editItemName(@RequestParam String name, @RequestParam String oldName,
			@RequestParam String newName) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (restaurant.getMenu().contains(itemRepository.findByName(oldName))) {
			Item newItem = itemRepository.findByName(oldName);
			newItem.setName(newName);
			restaurant.getMenu().remove(itemRepository.findByName(oldName));
			restaurant.getMenu().add(newItem);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_edit_item_description", method = RequestMethod.GET)
	public ModelAndView editItemDescription(@RequestParam String name, @RequestParam String oldName,
			@RequestParam String newName) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (restaurant.getMenu().contains(itemRepository.findByName(oldName))) {
			Item newItem = itemRepository.findByName(oldName);
			newItem.setDescription(newName);
			restaurant.getMenu().remove(itemRepository.findByName(oldName));
			restaurant.getMenu().add(newItem);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_edit_item_price", method = RequestMethod.GET)
	public ModelAndView editItemPrice(@RequestParam String name, @RequestParam String oldName,
			@RequestParam int newPrice) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (restaurant.getMenu().contains(itemRepository.findByName(oldName))) {
			Item newItem = itemRepository.findByName(oldName);
			newItem.setPrice(newPrice);
			restaurant.getMenu().remove(itemRepository.findByName(oldName));
			restaurant.getMenu().add(newItem);
			restaurantRepository.saveAndFlush(restaurant);
		}
		mav.addObject("restaurant", restaurant);
		return mav;
	}

	@RequestMapping(value = "/owner/edit_restaurant_add_item", method = RequestMethod.GET)
	public ModelAndView addRestaurantItem(@RequestParam String name, Item item) {
		ModelAndView mav = new ModelAndView("viewRestaurant");
		Restaurant restaurant = restaurantRepository.findByName(name);
		restaurant.getMenu().add(item);
		itemRepository.save(item);
		restaurantRepository.saveAndFlush(restaurant);
		mav.addObject("restaurant", restaurant);
		return mav;
	}

}
