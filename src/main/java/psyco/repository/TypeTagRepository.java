package psyco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import psyco.entity.TypeTag;

@Transactional
public interface TypeTagRepository extends JpaRepository<TypeTag, Integer> {

	@Query("select o from TypeTag o where o.parent = null order by id asc")
	public List<TypeTag> getRoots();
	

	
}