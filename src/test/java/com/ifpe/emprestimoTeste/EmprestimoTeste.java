package com.ifpe.emprestimoTeste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.emprestimo.Emprestimo;

public class EmprestimoTeste {

	Emprestimo emprestimo;

	@Before
	public void init() {
		emprestimo = new Emprestimo("1234567", "c1", "19/03/2022");
	}

	@Test
	public void getSiapeTeste() {
		emprestimo.getSiapeProf();
		assertTrue("1234567", true);
	}

	@Test
	public void setSiapeTeste() {
		emprestimo.setSiapeProf("7777777");
		assertTrue(emprestimo.getSiapeProf() == "7777777");
	}	

	@Test
	public void getCodItemTeste() {
		emprestimo.getCodItem();
		assertTrue("c1", true);
	}

	@Test
	public void setCodItemTeste() {
		emprestimo.setCodItem("c3");
		assertTrue(emprestimo.getCodItem() == "c3");
	}	

	@Test
	public void getDataTeste() {
		emprestimo.getData();
		assertTrue("09/03/2022", true);
	}

	@Test
	public void setDataTeste() {
		emprestimo.setData("20/03/2022");
		assertTrue(emprestimo.getData() == "20/03/2022");
	}	
}
