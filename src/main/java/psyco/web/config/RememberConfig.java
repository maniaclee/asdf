package psyco.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

@Component
public class RememberConfig {
	public static final String RememberTargetUrl = "RememberTargetUrl";
	@Autowired
	DataSource dataSource;
	boolean enabled = true;

	/**
	 * config for SpringSecurity
	 */
	public void configSecurity(HttpSecurity http) throws Exception {
		http.rememberMe().tokenRepository(bean_persistentTokenRepository()).tokenValiditySeconds(1209600);// 2 weeks
	}

	public boolean enabled() {
		return enabled;
	}

	/**
	 * Check if user is login by remember me cookie, refer org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
	public boolean isRememberMeAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication == null ? false : RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	/**
	 * save targetURL in session
	 */
	public void setRememberMeTargetUrlToSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute(RememberTargetUrl, "/admin/update");
		}
	}

	/**
	 * get targetURL from session
	 */
	public String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			targetUrl = session.getAttribute(RememberTargetUrl) == null ? "" : session.getAttribute(RememberTargetUrl).toString();
		}
		return targetUrl;
	}

	/**
	 * remember me
	 */
	PersistentTokenRepository bean_persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		db.setCreateTableOnStartup(true);
		return db;
	}

	/**
	 * remember me
	 */
	SavedRequestAwareAuthenticationSuccessHandler bean_savedRequestAwareAuthenticationSuccessHandler() {

		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter(RememberTargetUrl);
		return auth;
	}

}
