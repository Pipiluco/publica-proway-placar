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
public class EmailValidator implements Validator<Object> {
	private static final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	@Override
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
