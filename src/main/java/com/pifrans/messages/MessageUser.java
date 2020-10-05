package com.pifrans.messages;

import org.springframework.stereotype.Component;

import com.pifrans.messages.enums.EnumCadUser;

@Component
public class MessageUser extends MessageGeneric {

	public void userSaveSuccess() {
		info(EnumCadUser.USER_SAVE_SUCCESS.getDescription());
	}

	public void reportPassword() {
		warn(EnumCadUser.REPORT_PASSWORD.getDescription());
	}

	public void reportFirstName() {
		warn(EnumCadUser.REPORT_FIRST_NAME.getDescription());
	}

	public void reportLastName() {
		warn(EnumCadUser.REPORT_LAST_NAME.getDescription());
	}

	public void reportEmail() {
		warn(EnumCadUser.REPORT_EMAIL.getDescription());
	}

	public void confirmPassword() {
		warn(EnumCadUser.CONFIRM_PASSWORD.getDescription());
	}

	public void modelPassword() {
		warn(EnumCadUser.MODEL_PASSWORD.getDescription());
	}

	public void choiceRole() {
		warn(EnumCadUser.CHOICE_ROLE.getDescription());
	}
	
}
