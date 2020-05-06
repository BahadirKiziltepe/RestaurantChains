package chains.restaurant.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chains.restaurant.application.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	Item findByName(String name);
}
