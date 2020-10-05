package life.pifrans.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import life.pifrans.models.Game;
import life.pifrans.models.Season;
import life.pifrans.services.GameService;
import life.pifrans.services.SeasonService;

/**
 * Classe Bean para manipulação de dados em páginas web
 */
@Named
@Scope(value = "view")
public class SeasonBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SeasonService seasonService;

	@Autowired
	private GameService gameService;

	@Autowired
	private Season season;

	private List<Season> seasons;

	private static final String PAGE_SEASONS = "/private/seasons.jsf";

	@PostConstruct
	public void init() {
		seasons = seasonService.findAll();
		tt(seasons);
	}

	/**
	 * Busca os {@link Game} referente a {@link Season} e atualizar no banco a
	 * pontuação mínima e máxima por {@link Season}
	 */
	public void updateMinMaxPoints(List<Season> seasons) {
		for (Season season : seasons) {
			season.setGames(gameService.findGamesBySeasonId(season.getId()));

			List<Game> games = season.getGames();
			season.setMinimumRecord(games.get(0).getTotalPoints());
			season.setMaximumRecord(games.get(0).getTotalPoints());

			for (Game game : games) {
				if (game.getTotalPoints() < season.getMinimumRecord()) {
					season.setMinimumRecord(game.getTotalPoints());
				}
				if (game.getTotalPoints() > season.getMaximumRecord()) {
					season.setMaximumRecord(game.getTotalPoints());
				}
				seasonService.update(season);
			}
		}
	}

	public void tt(List<Season> seasons) {
		for (Season season : seasons) {
			season.setGames(gameService.findGamesBySeasonId(season.getId()));
			season.setMinimumRecord(gameService.findMinPoints(season.getId()));
			season.setMaximumRecord(gameService.findMaxPoints(season.getId()));
			seasonService.update(season);
		}
	}

	public List<Season> findAll() {
		seasons = new ArrayList<>();
		seasons = seasonService.findAll();
		return seasons;
	}

	public void find() {
		season = seasonService.find(season.getId());
	}

	public void renew() {
		season = new Season();
	}

	public String save() {
		season.setMinimumRecord(0);
		season.setMaximumRecord(0);
		seasonService.insert(season);
		renew();
		return PAGE_SEASONS;
	}

	public String update() {
		seasonService.update(season);
		return PAGE_SEASONS;
	}

	public String delete(Season season) {
		seasonService.delete(season.getId());
		return PAGE_SEASONS;
	}

	public String delete() {
		seasonService.delete(season.getId());
		season = new Season();
		return PAGE_SEASONS;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

}
