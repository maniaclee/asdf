package psyco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psyco.entity.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    public List<Subject> findByCode(int code);

    @Query("select s from Subject s where s.parent.id =:parentID")
    public List<Subject> findByParent(@Param("parentID")int parentID);

    public List<Subject>  findByLevelOrderById(int level);

    @Query("select s from Subject s where s.level =1 and s.group=:group")
    public List<Subject>  findByGroup(@Param("group") int group);
}
