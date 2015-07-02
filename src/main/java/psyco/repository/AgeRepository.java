package psyco.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.stereotype.Repository;
import psyco.entity.Age;

@Resource(name = "shit", type = JpaRepository.class)
@RepositoryRestResource(collectionResourceRel = "age", path = "age")
public interface AgeRepository extends JpaRepository<Age, Integer> {

	@Query("select a from Age a order by a.age asc")
	public List<Age> findOrderByAgeAsc();
}
