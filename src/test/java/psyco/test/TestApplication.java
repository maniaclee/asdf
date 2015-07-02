package psyco.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by lipeng on 15/6/18.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("psyco.repository")
@EnableJpaRepositories(basePackages = "psyco.repository")
//Exception: Not a managable class
@EntityScan("psyco.entity" )
public class TestApplication {
}
