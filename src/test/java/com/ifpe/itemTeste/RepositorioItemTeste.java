package com.ifpe.itemTeste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.ado.item.RepositorioItem;
import com.ifpe.item.Item;

public class RepositorioItemTeste {

	RepositorioItem repoItem;
	Item item;

	@Before
	public void init() {
		repoItem = new RepositorioItem();
		item = new Item("P4", "Projetor");

	}

	@Test
	public void incluirItemTeste() {
		int status = repoItem.inserir(item);
		assertTrue(status == 1);
	}

	@Test
	public void removerItemTeste() {
		repoItem.inserir(item);
		int status = repoItem.remover(item.getCodigoItem());
		assertTrue(status == 1);
	}


	@Test
	public void removerItemErroTeste() {
		int status = repoItem.remover(" kkkka");	
		assertTrue(status == 0);
	}

}
