package life.pifrans.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import life.pifrans.models.Game;
import life.pifrans.models.Score;
import life.pifrans.services.GameService;
import life.pifrans.services.ScoreService;

/**
 * Classe Bean para manipulação de dados em páginas web
 */
@Named
@Scope(value = "view")
public class GameBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private GameService gameService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private Game game;

	private List<Game> games;
	private Date currentDate = new Date();

	private static final String PAGE_GAMES = "/private/games.jsf";

	@PostConstruct
	public void init() {
		games = gameService.findAll();
		updateTotalPoints(games);
	}

	/**
	 * Busca os {@link Score} referente ao {@link Game} e atualizar no banco a
	 * pontuação do {@link Game}
	 */
	public void updateTotalPoints(List<Game> games) {
		for (Game game : games) {
			game.setScores(scoreService.findScoresByGamesId(game.getId()));
			game.setTotalPoints(scoreService.findPointsByGameId(game.getId()));
			gameService.update(game);
		}
	}

	public void findAll() {
		games = new ArrayList<>();
		games = gameService.findAll();

	}

	public void find() {
		game = gameService.find(game.getId());
	}

	public void renew() {
		game = new Game();
	}

	public String save() {
		gameService.insert(game);
		renew();
		return PAGE_GAMES;
	}

	public String update() {
		gameService.update(game);
		return PAGE_GAMES;
	}

	public String delete() {
		gameService.delete(game.getId());
		return PAGE_GAMES;
	}

	public String delete(Game game) {
		gameService.delete(game.getId());
		return PAGE_GAMES;
	}

	public List<Game> findGamesBySeasonId() {
		games = gameService.findGamesBySeasonId(game.getSeason().getId());
		return games;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
}
