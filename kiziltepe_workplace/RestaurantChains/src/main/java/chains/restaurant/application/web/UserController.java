package chains.restaurant.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import chains.restaurant.application.model.Restaurant;
import chains.restaurant.application.model.User;
import chains.restaurant.application.repository.RestaurantRepository;
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
    public String registrationOwner(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
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

    @RequestMapping(value = {"", "/","/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome(Model model) {
    	ModelAndView mav = new ModelAndView("welcome");
    	Iterable<Restaurant> restaurantList = restaurantRepository.findAll();
    	mav.addObject("restaurantList", restaurantList);
        return mav;
    }
}
