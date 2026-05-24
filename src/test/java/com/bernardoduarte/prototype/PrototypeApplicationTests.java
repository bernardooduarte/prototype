package com.bernardoduarte.prototype;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrototypeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveClonarTaxaSemCompartilharEstado() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		TaxaCambio original = gerenciador.registrarTaxa("USD", 5.17);
		TaxaCambio copia = gerenciador.clonarTaxa("USD", 5.30);

		assertNotNull(copia);
		assertNotSame(original, copia);
		assertEquals(5.17, original.getValorEmReais());
		assertEquals(5.30, copia.getValorEmReais());
		assertEquals(2, gerenciador.listarTaxas().size());
	}

	@Test
	void deveExibirPainelComTaxasClonadas() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		gerenciador.registrarTaxa("EUR", 5.62);
		gerenciador.clonarTaxa("EUR", 5.75);

		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			assertDoesNotThrow(() -> System.out.println(gerenciador.exibirPainel()));
		} finally {
			System.setOut(saidaOriginal);
		}

		String painel = saida.toString();
		assertTrue(painel.contains("Gerenciador de Taxa de Cambio (Prototype)"));
		assertTrue(painel.contains("Moeda: EUR | Taxa: EUR 5.62"));
		assertTrue(painel.contains("Moeda: EUR | Taxa: EUR 5.75"));
	}

	@Test
	void deveLancarExcecaoParaMoedaNaoSuportada() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		assertThrows(IllegalArgumentException.class, () -> gerenciador.clonarTaxa("JPY", 1.0));
	}
}
