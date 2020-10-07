package life.pifrans.beans;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import life.pifrans.models.Player;
import life.pifrans.services.PlayerService;

@Named
@Scope(value = "application")
public class UserLoggedBean {

	private Player player = new Player();

	@Autowired
	private PlayerService playerService;

	public boolean onLogged() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return true;
		} else {
			return false;
		}
	}

	public String getUserTheme() {
		if (onLogged()) {
			player = playerService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			if (player.getTheme() == null || player.getTheme() == "") {
				return "nova-light";
			} else {
				return player.getTheme();
			}
		} else {
			return "luna-amber";
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
