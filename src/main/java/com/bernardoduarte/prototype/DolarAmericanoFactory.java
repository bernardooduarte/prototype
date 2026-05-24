package com.bernardoduarte.prototype;

public class DolarAmericanoFactory extends TaxaCambioFactory {
	@Override
	public TaxaCambio criarTaxa(double valorEmReais) {
		return new DolarAmericano(valorEmReais, criarFormatadorValor());
	}

	@Override
	public FormatadorValor criarFormatadorValor() {
		return new FormatadorDolarAmericano(new FormatadorBase());
	}
}


