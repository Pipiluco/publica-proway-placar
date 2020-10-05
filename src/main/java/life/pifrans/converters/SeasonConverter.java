package life.pifrans.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.joinfaces.autoconfigure.butterfaces.ButterfacesProperties.Integration.Primefaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import life.pifrans.models.Season;
import life.pifrans.services.SeasonService;

/**
 * Classe {@link Converter} para trabalhar com componetes selectOneMenu do tipo
 * {@link Season} no JSF ou {@link Primefaces}
 */
@Component
public class SeasonConverter implements Converter<Season> {
	@Autowired
	private SeasonService seasonService;

	@Override
	public Season getAsObject(FacesContext context, UIComponent component, String value) {
		Season season = seasonService.find(new Long(value));
		return season;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Season value) {
		if (value instanceof Season) {
			Season season = (Season) value;
			return season.getId().toString();
		} else {
			return null;
		}
	}
}
