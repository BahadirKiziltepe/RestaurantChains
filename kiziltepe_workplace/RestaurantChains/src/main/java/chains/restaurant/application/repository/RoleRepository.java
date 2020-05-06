package chains.restaurant.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chains.restaurant.application.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
		Role findById(long id);
}
