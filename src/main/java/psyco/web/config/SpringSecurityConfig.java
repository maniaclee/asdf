package psyco.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("psyco")
@EnableWebSecurity
@EnableTransactionManagement
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	RememberConfig rememberConfig;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder()).usersByUsernameQuery("select email,password , enabled from User where email=?").authoritiesByUsernameQuery("select email, role from User where email=?");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry au = http.authorizeRequests();
		// au.antMatchers("/login**").permitAll();
		// au.antMatchers("/regist").permitAll();
		// au.antMatchers("/registSubmit").permitAll();
		au.antMatchers("/my").hasAnyRole("USER", "ADMIN");
		au.antMatchers("/antiqueModify*").hasAnyRole("USER");
		au.antMatchers("/admin").hasRole("ADMIN");
		// au.antMatchers("/css/**").permitAll();
		// au.antMatchers("/js/**").permitAll();
		// au.antMatchers("/lib/**").permitAll();
		// au.antMatchers("/captcha-image").permitAll();
		au.antMatchers("/**").permitAll();
		// au.antMatchers("/**").hasRole("USER").anyRequest().authenticated();

		/* login page and login-submit-url is the same but differ in GET & POST */
		http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index").failureUrl("/login?t=failed").usernameParameter("email").passwordParameter("password");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?t=out").deleteCookies("JSESSIONID").invalidateHttpSession(true);

		/* remember me */
		if (rememberConfig.enabled())
			rememberConfig.configSecurity(http);

		http.sessionManagement().invalidSessionUrl("/login?t=invalidSession").maximumSessions(1);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

}