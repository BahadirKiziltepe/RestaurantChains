package chains.restaurant.application.service;

import chains.restaurant.application.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
