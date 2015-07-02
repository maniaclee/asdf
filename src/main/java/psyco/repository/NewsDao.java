package psyco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import psyco.entity.News;

public interface NewsDao extends JpaRepository<News, Long> {

}
