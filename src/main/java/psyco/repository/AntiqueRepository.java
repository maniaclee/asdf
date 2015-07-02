package psyco.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import psyco.entity.Antique;
import psyco.entity.User;
import psyco.web.SearchFormData;

@Transactional
@RepositoryRestResource(collectionResourceRel = "data", path = "item")
@Repository
public interface AntiqueRepository extends JpaRepository<Antique, Long> {

    public static final int PAGE_SIZE = 40;

    static final String SearchSQL_FormData_Basic = "select o from Antique o where  o.title like %:#{#fd.key}% and o.price >= :#{#fd.priceMin} and o.price <= :#{#fd.priceMax} ";
    static final String SearchSQL_FormData_Basic_WithType = "select o from Antique o where o.type.id= :#{#fd.type} and o.title like %:#{#fd.key}% and o.price >= :#{#fd.priceMin} and o.price <= :#{#fd.priceMax} ";
    static final String SearchSQL_FormData_Exchange = " and o.status = 0 and o.user.id != :#{#u.id} and o.user.level <=:#{#u.level}  ";

    @Query("select o from Antique o where o.title like %:key%")
    public List<Antique> search(@Param("key") String key);

    @Query("select o from Antique o where o.title like %:key%")
    public Page<Antique> search(@Param("key") String key, Pageable pageable);

    @Query("select o from Antique o where o.title like %:key% and o.price >=:priceMin and o.price<=:priceMax")
    public Page<Antique> search(@Param("key") String key, @Param("priceMin") double priceMin, @Param("priceMax") double priceMax, Pageable pageable);

    @Query("select o from Antique o where o.title like %:key% and o.type.id= :typeID and o.price >=:priceMin and o.price<=:priceMax")
    public Page<Antique> search(@Param("key") String key, @Param("priceMin") double priceMin, @Param("priceMax") double priceMax, @Param("typeID") int typeID, Pageable pageable);

    /*------------------------------------------------*/
    @Query(SearchSQL_FormData_Basic)
    public Page<Antique> search(@Param("fd") SearchFormData fd, Pageable pageable);

    @Query(SearchSQL_FormData_Basic_WithType)
    public Page<Antique> searchWithType(@Param("fd") SearchFormData fd, Pageable pageable);

    @Query(SearchSQL_FormData_Basic_WithType + SearchSQL_FormData_Exchange)
    public Page<Antique> searchWithTypeExchange(@Param("fd") SearchFormData fd, @Param("u") User u, Pageable pageable);

    @Query(SearchSQL_FormData_Basic + SearchSQL_FormData_Exchange)
    public Page<Antique> searchWithExchange(@Param("fd") SearchFormData fd, @Param("u") User u, Pageable pageable);

    @Query("select o from Antique o where o.user.id = :userID and o.status = 0 ORDER by o.dateLastModify desc")
    public List<Antique> getMyItemsExchangable(@Param("userID") long userID);

    /**
     * search by Object
     */
    @Query("select o from Antique o where o.type.id =:type")
    public Page<Antique> searchByType(@Param("type") int type, Pageable pageable);

    public List<Antique> findByTitleContaining(String key);

    public Page<Antique> findByTitleContaining(String key, Pageable pageable);

    @Query("select o from Antique o where o.user.id = :userID order by o.dateLastModify desc")
    public List<Antique> findByUser(@Param("userID") long userID);

    @Query("select o from Antique o where o.user.id = :userID and o.type!=null and o.type.id = :typeID order by o.dateLastModify desc")
    public List<Antique> findByUserAndType(@Param("userID") long userID, @Param("typeID") int typeID);
    @Query("select o from Antique o where o.user.id = :userID and o.type!=null and o.type.id = :typeID order by o.dateLastModify desc")
    public List<Antique> findByUserAndType(@Param("userID") long userID, @Param("typeID") int typeID ,  Pageable pageable);

    @Query("select o from Antique o where :level >= o.user.level and o.status=0")
    public List<Antique> findByUserLevel(@Param("level") int level);

    @Query("select o from Antique o order by o.dateUpload desc ")
    public List<Antique> itemsOrderByDateupload(Pageable pageable);

    @Query("select o.id,o.title from Antique o where :level >= o.user.level and o.status=0")
    public List<Object[]> test(@Param("level") int level);


}