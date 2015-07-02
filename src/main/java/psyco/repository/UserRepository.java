package psyco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import psyco.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select i from User i where i.name = ?1")
	public List<User> find(String name);

	public User findByName(String name);

	public User findByEmail(String email);

}