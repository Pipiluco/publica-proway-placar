package life.pifrans.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pifrans.messages.MessageUser;

import life.pifrans.models.Player;
import life.pifrans.models.Role;
import life.pifrans.services.PlayerService;

/**
 * Classe Bean para manipulação de dados em páginas web
 */
@Named
@Scope(value = "view")
public class PlayerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private Player player;

	private MessageUser messageUser = new MessageUser();

	private List<Player> players;
	private static final String PAGE_PLAYERS = "/private/players.jsf";

	@PostConstruct
	public void init() {
		players = playerService.findAll();
	}

	public List<Player> findAll() {
		players = new ArrayList<>();
		players = playerService.findAll();
		return players;
	}

	public void find() {
		player = playerService.find(player.getId());
	}

	public Player find(Long id) {
		player = playerService.find(id);
		return player;
	}

	public void renew() {
		player = new Player();
	}

	public String save() {
		player.setPassword(encoder.encode(player.getPassword()));
		player.setCurrentAccess(null);
		player.setLastAccess(null);
		player.setActive(true);
		if (player.getRole() == null) {
			player.setRole(new Role(1L, "ROLE_USER"));
		}
		if (player.getTheme() == null || player.getTheme() == "") {
			player.setTheme("nova-light");
		}
		playerService.insert(player);
		messageUser.userSaveSuccess();
		renew();
		return PAGE_PLAYERS;
	}

	public String update() {
		playerService.update(player);
		return PAGE_PLAYERS;
	}

	public String delete(Player player) {
		playerService.delete(player.getId());
		return PAGE_PLAYERS;
	}

	public String delete() {
		playerService.delete(player.getId());
		renew();
		return PAGE_PLAYERS;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
