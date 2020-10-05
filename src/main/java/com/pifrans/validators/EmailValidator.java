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
public class EmailValidator implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		String field = (String) object;
		MessageGeneric message = new MessageGeneric();

		if (!field.matches(EMAIL_PATTERN)) {
			((UIInput) component).setValid(false);
			message.warn(EnumCadUser.INCORRECT_EMAIL.getDescription());
			message.warn(EnumCadUser.MODEL_EMAIL.getDescription());
		}
	}
}
