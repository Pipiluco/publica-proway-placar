package com.pifrans.validators;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import com.pifrans.messages.MessageGeneric;
import com.pifrans.messages.enums.EnumCadUser;

@Named
public class PasswordValidator implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		String field = (String) object;
		MessageGeneric message = new MessageGeneric();

		if (field.length() < 8 || field.length() > 16) {
			((UIInput) component).setValid(false);
			message.warn(EnumCadUser.PASSWORD_SIZE.getDescription());
		} else if (!field.matches(PASSWORD_PATTERN)) {
			((UIInput) component).setValid(false);
			message.warn(EnumCadUser.MODEL_PASSWORD.getDescription());
		}
	}
}
