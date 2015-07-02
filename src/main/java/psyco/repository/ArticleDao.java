package psyco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import psyco.entity.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {

	
}
