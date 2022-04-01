package com.ifpe.ado.emprestimoTeste;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.ado.emprestimo.RepositorioEmprestimo;
import com.ifpe.emprestimo.Emprestimo;
import com.ifpe.excecoes.SiapeInvalidoException;
import com.ifpe.excecoes.TelefoneInvalidoException;
import com.ifpe.item.Item;
import com.ifpe.professor.Professor;

public class RepositorioEmprestimoTeste {

	RepositorioEmprestimo repoEmpr;
	Emprestimo emprestimo;
	Professor professor;
	Item item;
	Timestamp times;


	@Before
	public void init() {
		repoEmpr = new RepositorioEmprestimo();
	}

	@Test
	public void efetuarEmprestimoTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		professor = new Professor("Fulano", "(11)98144-1111", "abcdefg");
		item = new Item("notebook", "n1");
		times = new Timestamp(System.currentTimeMillis());
		emprestimo = new Emprestimo(professor.getSiape(), item.getCodigoItem(), times.toString());
		int status = repoEmpr.efetuarEmprestimo(emprestimo);
		assertTrue(status == 1);
	}

	@Test
	public void efetuarEmprestimoErroTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		professor = new Professor("Fulano", "(11)98144-1111", "1111111");
		item = new Item("notebook", "n5");
		times = new Timestamp(System.currentTimeMillis());
		emprestimo = new Emprestimo(professor.getSiape(), item.getCodigoItem(), times.toString());
		int status = repoEmpr.efetuarEmprestimo(emprestimo);
		assertTrue(status == 0);
	}

	@Test(expected = TelefoneInvalidoException.class)
	public void efetuarEmprestimoErroTelefoneTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		professor = new Professor("Fulano", "(11)44-1111", "1111111");
		item = new Item("notebook", "n5");
		times = new Timestamp(System.currentTimeMillis());
		emprestimo = new Emprestimo(professor.getSiape(), item.getCodigoItem(), times.toString());
		int status = repoEmpr.efetuarEmprestimo(emprestimo);
		assertTrue(status == 0);
	}

	@Test(expected = SiapeInvalidoException.class)
	public void efetuarEmprestimoErroSiapeTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		professor = new Professor("Fulano", "(11)91144-1111", "111");
		item = new Item("notebook", "n5");
		times = new Timestamp(System.currentTimeMillis());
		emprestimo = new Emprestimo(professor.getSiape(), item.getCodigoItem(), times.toString());
		int status = repoEmpr.efetuarEmprestimo(emprestimo);
		assertTrue(status == 0);
	}
	//
	//	@Test
	//	public void removerEmprestimoTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
	//		professor = new Professor("Fulano", "(11)91144-1111", "1234567");
	//		item = new Item("notebook", "p2");
	//		times = new Timestamp(System.currentTimeMillis());
	//		emprestimo = new Emprestimo(professor.getSiape(), item.getCodigoItem(), times.toString());
	//		int id = Integer.parseInt(item.getCodigoItem());
	//		int status = repoEmpr.removerEmprestimo(id);
	//		assertTrue(status == 1);
	//
	//	}
	//


}

