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

public class FacadeTeste {

	Facade facade;

	Professor prof;
	Item item;
	Emprestimo emprestimo;
	Timestamp times;

	@Before
	public void init() throws TelefoneInvalidoException, SiapeInvalidoException {

		facade = new Facade();

		prof = new Professor("Eva", "(87)12345-9876", "1234567");
		item = new Item("notebook", "n2");
		times = new Timestamp(System.currentTimeMillis());
		emprestimo = new Emprestimo(prof.getSiape(), item.getCodigoItem(), times.toString());
	}

	//	@Test
	//	public void inserirProfessorTeste() {
	//		int status = facade.inserirProfessor(prof);
	//		assertTrue(status == 1);
	//	}
	//
	//	@Test
	//	public void removerProfessorTeste() {
	//		int status = facade.removerProfessor(prof.getSiape());
	//		assertTrue(status == 1);
	//	}
	//
	//	@Test
	//	public void inserirItemTeste() {
	//		int status = facade.inserirItem(item);
	//		assertTrue(status == 1);
	//	}
	//
	//	@Test
	//	public void removerItemTeste() {
	//		int status = facade.removerItem(item.getCodigoItem());
	//		assertTrue(status == 1);
	//	}

	@Test
	public void inserirEmprestimoTeste() {
		int status = facade.inserirEmprestimo(emprestimo);
		assertTrue(status == 1);
	}

	//	@Test
	//	public void removerEmprestimoTeste() {
	//		int status = controlEmprestimo.remover(emprestimo);
	//		assertTrue(status == 1);
	//	}


}
