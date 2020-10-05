package life.pifrans.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.joinfaces.autoconfigure.butterfaces.ButterfacesProperties.Integration.Primefaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import life.pifrans.models.Role;
import life.pifrans.services.RoleService;

/**
 * Classe {@link Converter} para trabalhar com componetes selectOneMenu do tipo
 * {@link Game} no JSF ou {@link Primefaces}
 */
@Component
public class RoleConverter implements Converter<Role> {
	@Autowired
	private RoleService roleService;

	@Override
	public Role getAsObject(FacesContext context, UIComponent component, String value) {
		Role role = roleService.find(new Long(value));
		return role;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Role value) {
		if (value instanceof Role) {
			Role role = (Role) value;
			return role.getId().toString();
		} else {
			return null;
		}
	}
}
