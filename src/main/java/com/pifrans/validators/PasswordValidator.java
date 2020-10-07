package com.pifrans.validators;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.pifrans.messages.MessageGeneric;
import com.pifrans.messages.enums.EnumCadUser;

@FacesValidator
public class PasswordValidator implements Validator<Object>{
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	@Override
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
