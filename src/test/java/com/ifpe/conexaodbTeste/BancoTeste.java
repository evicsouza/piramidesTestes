package com.ifpe.conexaodbTeste;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.conexaodb.Banco;

public class BancoTeste {

	Banco banco;

	@Before
	public void init() {
		banco = new Banco();
	}

	@Test
	public void abrirConexaoTeste() throws Exception {
		banco.abrirConexao();
	}

	@Test(expected=Exception.class)
	public void fecharConexaoTeste() throws Exception {
		banco.fecharConexao();
	}
}
