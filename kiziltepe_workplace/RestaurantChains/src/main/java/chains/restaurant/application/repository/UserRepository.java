package chains.restaurant.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chains.restaurant.application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);   
}
