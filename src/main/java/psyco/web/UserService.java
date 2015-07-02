package psyco.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import psyco.entity.User;
import psyco.repository.UserRepository;

@Component
public class UserService {
	static final String ROLE_USER = "ROLE_USER";
	public static final String __SessionUser_Identifier = "USER";
	@Autowired
	UserRepository rep;
	@Autowired
	PasswordEncoder codec;
	@Autowired
	AuthenticationManager authenticationManager;

	public User checkNewUser(User user) throws Exception {
		String email = user.getEmail();
		if (email == null || email.isEmpty())
			throw new Exception("邮箱不能为空");
		if (rep.findByEmail(email) != null)
			throw new Exception("邮箱已被注册");
		user.setRole(ROLE_USER);
		user.setEnabled(true);
		String passwordUn = user.getPassword();
		user.setPassword(codec.encode(user.getPassword()));
		rep.save(user);
		user.setPassword(passwordUn);
		return user;
	}

	public UserRepository getRepository() {
		return rep;
	}

	/**
	 * perform login authentication
	 */
	public void performLogin(User user, HttpServletRequest request) throws Exception {
		if (rep.findByEmail(user.getEmail()) == null)
			throw new Exception("用户不存在");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority(ROLE_USER) }));
		auth.setDetails(new WebAuthenticationDetails(request));
		try {
			Authentication result = authenticationManager.authenticate(auth);
			if (auth.isAuthenticated()) {
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(result);
				HttpSession session = request.getSession(true);
				session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
				session.setAttribute(__SessionUser_Identifier, rep.findByEmail(user.getEmail()));
			}
		} catch (AuthenticationException e) {
			throw new Exception("用户名或密码错误");
		}
	}

	public static User getUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(__SessionUser_Identifier);
	}

	public void registAndLogin(User user, HttpServletRequest request) throws Exception {
		performLogin(checkNewUser(user), request);
	}

}
