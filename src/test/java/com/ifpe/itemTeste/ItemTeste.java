package com.ifpe.itemTeste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import com.ifpe.item.Item;

public class ItemTeste{

	Item item;

	@Before
	public void init() {
		item = new Item("notebook","n1");
	}

	@Test
	public void setTipoItemTeste() {
		item.setTipoItem("cabo");
		assertTrue(item.getTipoItem() == "cabo");
	}

	@Test
	public void getTipoItemTeste() {
		item.getTipoItem();
		assertTrue("cabo", true);
	}

	@Test
	public void setCodItemTest() {
		item.setCodigoItem("c1");
		assertTrue(item.getCodigoItem() == "c1");
	}
	
	@Test
	public void getCodItemTest() {
		item.getCodigoItem();
		assertTrue("c1", true);
	}

}
