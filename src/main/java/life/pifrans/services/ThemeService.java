package life.pifrans.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.pifrans.messages.enums.EnumThemesPrimefaces;

import life.pifrans.models.Theme;

@Named
@Scope(value = "view")
public class ThemeService implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Theme> themes;

	@PostConstruct
	public void init() {
		themes = new ArrayList<>();
		for (EnumThemesPrimefaces theme : EnumThemesPrimefaces.values()) {
			themes.add(new Theme(theme.getId(), theme.getDisplayName(), theme.getName()));
		}
	}

	public List<Theme> getThemes() {
		return themes;
	}
}
