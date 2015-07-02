package psyco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import psyco.entity.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

	@Query("select o from Exchange o where o.user.id = :userID")
	public List<Exchange> findByUserID(@Param("userID") long userID);

	@Query("select o from Exchange o where o.src.id = :antiqueID")
	public List<Exchange> findBySrc(@Param("antiqueID") int antiqueID);

	@Query("select o from Exchange o where o.src.id = :antiqueID and o.user.id = :userID")
	public Exchange findBySrcAndUser(@Param("antiqueID") long antiqueID, @Param("userID") int userID);

	public List<Exchange> findByStatusLessThan(int status);
}
