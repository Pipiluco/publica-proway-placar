package life.pifrans.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import life.pifrans.models.Theme;
import life.pifrans.services.ThemeService;

@Named
@Scope(value = "view")
public class ThemeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Theme> themes;

	@Inject
	private ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setService(ThemeService service) {
		this.service = service;
	}
}
