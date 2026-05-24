package com.bernardoduarte.prototype;

import java.util.Locale;

public class FormatadorBase extends FormatadorValor {

	@Override
	public String formatar(double valor) {
		return String.format(Locale.US, "%.2f", valor);
	}
}


