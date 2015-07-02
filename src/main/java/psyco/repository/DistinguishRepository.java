package psyco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import psyco.entity.Distinguish;

public interface DistinguishRepository extends JpaRepository<Distinguish, Long> {

	@Query("select o from Distinguish o where o.user.id = :userID")
	public List<Distinguish> findByUserID(@Param("userID") long userID);

	@Query("select o from Distinguish o where o.item.id = :antiqueID and o.user.id = :userID")
	public Distinguish findBySrcAndUser(@Param("antiqueID") long antiqueID, @Param("userID") int userID);

	public List<Distinguish> findByStatusLessThan(int status);

}
