package psyco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import psyco.entity.City;

@Transactional
public interface CityRepository extends JpaRepository<City, Integer> {

	@Query("select o from City o where o.id = 1")
	public City getChina();

	@Query("select o from City o where o.type = 1")
	public List<City> getProvinces();

	@Query("select o from City o where o.parent.id = :province")
	public List<City> getCities(@Param("province") int province);
	
	@Query("select o from City o where o.parent.id = :city")
	public List<City> getDistricts(@Param("city") int city);

	public List<City> findByName(String name);

}