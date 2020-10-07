package life.pifrans.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import life.pifrans.models.Player;
import life.pifrans.services.PlayerService;


@Named
@Scope(value = "view")
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PlayerService repository;

	private Player player = new Player();

	/*
	 * Verifica se o usuário está logado e se estiver o impedi de acessar a página
	 * de login fazendo o redirecionamento para a página determinada
	 */
	public void onLogged() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String url = "/private/scoreboard.jsf";

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@PostConstruct
	public void init() {
		player = repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
