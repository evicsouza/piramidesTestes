package com.ifpe.facadeTeste;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.emprestimo.Emprestimo;
import com.ifpe.excecoes.SiapeInvalidoException;
import com.ifpe.excecoes.TelefoneInvalidoException;
import com.ifpe.facade.Facade;
import com.ifpe.item.Item;
import com.ifpe.professor.Professor;

public class FacadeIncluTeste {

	Facade facade;

	Professor prof;
	Item item;
	Emprestimo emprestimo;
	Timestamp times;

	@Before
	public void init() throws TelefoneInvalidoException, SiapeInvalidoException {

		facade = new Facade();
		item = new Item("123", "notebook");


	}

	@Test
	public void inserirProfessorTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		prof = new Professor("Eva", "(87)12345-9876", "asdf5hj");
		item = new Item("notebook", "n7");
		int status = facade.inserirProfessor(prof);
		assertTrue(status == 1);
	}

	@Test
	public void removerProfessorTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		prof = new Professor("Eva", "(87)12345-9876", "asdf5hj");
		int status = facade.removerProfessor(prof.getSiape());
		assertTrue(status == 1);
	}

	@Test
	public void inserirProfessorErroTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		prof = new Professor("Eva", "(87)12345-9876", "asdf5hj");
		item = new Item("notebook", "n7");
		int status = facade.inserirProfessor(prof);
		assertTrue(status == 0);
	}

	@Test(expected = SiapeInvalidoException.class)
	public void removerProfessorErroTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		prof = new Professor("Eva", "(87)12345-9876", "a");
		int status = facade.removerProfessor(prof.getSiape());
		assertTrue(status == 0);
	}

	@Test
	public void inserirItemErroTeste() {
		item = new Item(" ", "notebook");
		int status = facade.inserirItem(item);
		assertTrue(status == 0);
	}

	@Test
	public void removerItemErroTeste() {
		item = new Item("a", "notebook");
		int status = facade.removerItem(item.getCodigoItem());
		assertTrue(status == 0);
	}

}
