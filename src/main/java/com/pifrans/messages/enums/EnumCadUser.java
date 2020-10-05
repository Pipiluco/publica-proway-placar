package com.pifrans.messages.enums;



public enum EnumCadUser {
	REPORT_FIRST_NAME("Informe o nome!"),
	REPORT_LAST_NAME("Informe o sobrenome!"),
	REPORT_EMAIL("Informe o e-mail!"),
	REPORT_PASSWORD("Informe a senha!"),
	CHOICE_ROLE("Escolha a permissão!"),
	CONFIRM_PASSWORD("Confirme a senha!"),
	INCORRECT_EMAIL("E-mail incorreto!"),
	MODEL_PASSWORD("A senha deve conter letras maiúsculas e minúsculas, números e símbolos!"),
	MODEL_EMAIL("Exemplo de e-mail (pifrans.technology@gmail.com)!"),
	PASSWORD_SIZE("A senha deve ter no mínimo 8 e no máximo 16 caracteres!"),
	USER_SAVE_SUCCESS("Usuário salvo com sucesso!");
	
	private String description;

	
	private EnumCadUser(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
