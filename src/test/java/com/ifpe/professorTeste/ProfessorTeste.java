package com.ifpe.professorTeste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.excecoes.SiapeInvalidoException;
import com.ifpe.excecoes.TelefoneInvalidoException;
import com.ifpe.professor.Professor;

public class ProfessorTeste {

	Professor professor;

	@Before
	public void init() throws TelefoneInvalidoException, SiapeInvalidoException {
		professor = new Professor("Eva", "(87)98144-6017", "1234567");
	}

	@Test
	public void getNomeTeste() {
		professor.getNome();
		assertTrue("Eva", true);
	}

	@Test
	public void setNomeTeste() {
		professor.setNome("Maria");
		assertTrue(professor.getNome() == "Maria");
	}

	@Test
	public void getTelefoneTeste() {
		professor.getTelefone();
		assertTrue("(87)98144-6017", true);
	}

	@Test
	public void setTelefoneTeste() {
		professor.setTelefone("(81)12345-6789");
		assertTrue(professor.getTelefone() == "(81)12345-6789");
	}

	@Test
	public void getSiapeTeste() {
		professor.getSiape();
		assertTrue("1234567", true);
	}

	@Test
	public void setSiapeTeste() {
		professor.setSiape("9876543");
		assertTrue(professor.getSiape() == "9876543");
	}

	@Test
	public void validaSiapeTeste() {
		assertTrue(professor.getSiape().length() == 7);
	}

	@Test(expected = TelefoneInvalidoException.class)
	public void telefoneExceptionTeste() throws SiapeInvalidoException, TelefoneInvalidoException {
		Professor prof = new Professor("Jose", "2345-6789", "123");
	}

	@Test(expected = SiapeInvalidoException.class)
	public void siapeExceptionTeste() throws SiapeInvalidoException, TelefoneInvalidoException {
		Professor prof = new Professor("Joao", "(87)12345-6789", "987");
	}

}
