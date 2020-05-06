package chains.restaurant.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.repository.RestaurantRepository;

@Controller
public class AdminController {
	@Autowired
	RestaurantRepository restaurantRepository;
    
    @RequestMapping(value = "/admin/register_restaurant", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("restaurantForm", new Restaurant());

        return "registerRestaurant";
    }

    @RequestMapping(value = "/admin/register_restaurant", method = RequestMethod.POST)
    public String registration(@ModelAttribute("restaurantForm") Restaurant restaurant, Model model) {
    	restaurantRepository.save(restaurant);

        return "registerRestaurant";
    }
	
}
