package life.pifrans.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import life.pifrans.models.Score;
import life.pifrans.services.ScoreService;

/**
 * Classe Bean para manipulação de dados em páginas web
 */
@Named
@Scope(value = "view")
public class ScoreBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private Score score;

	private List<Score> scores;
	private static final String PAGE_SCORES = "/private/scores.jsf";

	@PostConstruct
	public void init() {
		scores = scoreService.findAll();
	}

	public List<Score> findAll() {
		scores = new ArrayList<>();
		scores = scoreService.findAll();
		return scores;
	}

	public void renew() {
		score = new Score();
	}

	public void find() {
		score = scoreService.find(score.getId());
	}

	public Score findMinRecord(Long id) {
		Score score = scoreService.findMinRecord(id);
		return score;
	}

	public Score findMaxRecord(Long id) {
		Score score = scoreService.findMaxRecord(id);
		return score;
	}

	public String save() {
		scoreService.insert(score);
		renew();
		return PAGE_SCORES;
	}

	public String update() {
		scoreService.update(score);
		return PAGE_SCORES;
	}

	public String delete(Score score) {
		scoreService.delete(score.getId());
		return PAGE_SCORES;
	}

	public String delete() {
		scoreService.delete(score.getId());
		renew();
		return PAGE_SCORES;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
}
