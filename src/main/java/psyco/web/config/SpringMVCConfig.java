package psyco.web.config;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import psyco.web.Conf;

import java.net.MalformedURLException;

@EnableWebMvc
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		addResource(registry,"css");
		addResource(registry,"js");
		addResource(registry,"img");
		addResource(registry,"lib");

//		registry.addResourceHandler("/img/**").addResourceLocations("classpath:static/img/").setCachePeriod(31556926);
		try {
			registry.addResourceHandler("/data-upload/**").addResourceLocations(Conf.DIR_Base.toURI().toURL().toString()).setCachePeriod(31556926);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	void addResource(ResourceHandlerRegistry registry ,String prefix){
		registry.addResourceHandler("/static/@/**".replace("@",prefix)).addResourceLocations("classpath:static/@/".replace("@",prefix)).setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	@Bean
//	public InternalResourceViewResolver getInternalResourceViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
//		// resolver.setPrefix("/");
//		// resolver.setSuffix(".html");
//		resolver.setPrefix("/resources/jsp/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver re = new CommonsMultipartResolver();
		re.setMaxUploadSize(100000000);
		return re;
	}

	@Bean
	public ServletContextInitializer initializer() {
		return new PsycoServletContextInitializer();
	}

}
