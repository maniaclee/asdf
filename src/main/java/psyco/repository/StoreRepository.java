package psyco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psyco.entity.Antique;
import psyco.entity.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s from Store s where s.user != null and s.user.id = :userID")
    public Store findByUser(@Param("userID") long user);


}
