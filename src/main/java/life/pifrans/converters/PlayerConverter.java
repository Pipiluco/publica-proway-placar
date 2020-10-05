package life.pifrans.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.joinfaces.autoconfigure.butterfaces.ButterfacesProperties.Integration.Primefaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import life.pifrans.models.Player;
import life.pifrans.services.PlayerService;

/**
 * Classe {@link Converter} para trabalhar com componetes selectOneMenu do tipo
 * {@link Player} no JSF ou {@link Primefaces}
 */
@Component
public class PlayerConverter implements Converter<Player> {
	@Autowired
	private PlayerService playerService;

	@Override
	public Player getAsObject(FacesContext context, UIComponent component, String value) {
		Player player = playerService.find(new Long(value));
		return player;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Player value) {
		if (value instanceof Player) {
			Player player = (Player) value;
			return player.getId().toString();
		} else {
			return null;
		}
	}
}
