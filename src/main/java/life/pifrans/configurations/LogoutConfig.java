package life.pifrans.configurations;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutConfig implements LogoutSuccessHandler {
	private static final Logger LOGGER = Logger.getLogger(LogoutConfig.class.getName());

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if (authentication != null) {
			LOGGER.info(authentication.getName() + " deslogado com sucesso!");
		}
		
		String loginPage = request.getContextPath() + "/public/login.jsf";
		response.setStatus(HttpStatus.OK.value());
		response.sendRedirect(loginPage);
	}
}
