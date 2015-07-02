package psyco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import psyco.entity.Favor;

public interface FavorRepository extends JpaRepository<Favor, Long> {

	@Query("select o from Favor o where o.user.id = :userID")
	public List<Favor> findByUserID(@Param("userID") long userID);

	@Query("select o from Favor o where o.antique.id = :antiqueID")
	public List<Favor> findByAntiqueID(@Param("antiqueID") int antiqueID);

	@Query("select o from Favor o where o.antique.id = :antiqueID and o.user.id = :userID")
	public Favor findByAntiqueAndUser(@Param("antiqueID") long antiqueID, @Param("userID") long userID);
}
