package life.pifrans.configurations;

import java.util.Date;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import life.pifrans.models.Player;
import life.pifrans.repositories.PlayerRepository;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationListener<AuthenticationSuccessEvent> {
	private static final Logger LOGGER = Logger.getLogger(SecurityConfig.class.getName());

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private LogoutConfig logoutConfig;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/templates/**", "/public/**").permitAll().antMatchers("/javax.faces.resource/**").permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/public/login.jsf").permitAll().defaultSuccessUrl("/private/scoreboard.jsf", true).failureUrl("/public/login.jsf?error=true");
		http.logout().logoutSuccessHandler(logoutConfig);
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT email, password, 'true' as enable FROM players WHERE email = ? AND is_active = true")
				.authoritiesByUsernameQuery("SELECT p.email, r.access FROM players p, roles r WHERE p.fk_role = r.id AND p.email = ?");
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		LOGGER.info(event.getAuthentication().getName() + " logado com sucesso!");
		
		Player user = playerRepository.findByEmail(event.getAuthentication().getName());
		user.setLastAccess(user.getCurrentAccess());
		user.setCurrentAccess(new Date(System.currentTimeMillis()));
		playerRepository.save(user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


