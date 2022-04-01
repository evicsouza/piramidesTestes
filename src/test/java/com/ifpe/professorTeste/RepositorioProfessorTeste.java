package com.ifpe.professorTeste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.ado.professor.IRepositorioProfessor;
import com.ifpe.ado.professor.RepositorioProfessor;
import com.ifpe.excecoes.SiapeInvalidoException;
import com.ifpe.excecoes.TelefoneInvalidoException;
import com.ifpe.professor.Professor;

public class RepositorioProfessorTeste {

	RepositorioProfessor repoProf;
	Professor professor;

	@Before
	public void init() throws TelefoneInvalidoException, SiapeInvalidoException {
		repoProf = new RepositorioProfessor();
		professor = new Professor("Eva","(87)98158-6017", "a291b25");

	}

	@Test
	public void incluirProfTeste() {
		int status = repoProf.inserir(professor);
		assertTrue(status == 1);
	}

	@Test
	public void removerProfTeste() {
		int status = repoProf.remover(professor.getSiape());
		assertTrue(status == 1);
	}

	@Test(expected = TelefoneInvalidoException.class)
	public void incluirProfExceptionTeste() throws TelefoneInvalidoException, SiapeInvalidoException {
		Professor profErro = new Professor("Ana", "8524862", "15488");
		int status = repoProf.inserir(profErro);
		assertTrue(status == 0);
	}

}
