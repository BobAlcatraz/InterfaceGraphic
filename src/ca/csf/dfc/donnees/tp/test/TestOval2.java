package ca.csf.dfc.donnees.tp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.dfc.donnees.tp.controller.Oval;

class TestOval2 {

	@Test
	void testConst1() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		assertEquals(7, oval.GetX());
	}
	@Test
	void testConst2() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		assertEquals(Color.BLACK, oval.GetCouleur());
	}
	
	@Test
	void testIsclicked()
	{
		Oval oval=new Oval(2,8 ,-4, 8, 4, Color.BLACK, Color.BLUE);
		//Point m=new Point(8, 4);
		assertEquals(true,oval.isclicked(7, 4));
		assertEquals(true,oval.isclicked(6, 4));
		assertEquals(true,oval.isclicked(5, 4));
		assertEquals(true,oval.isclicked(4, 4));
		assertEquals(true,oval.isclicked(8, 4));
		assertEquals(true,oval.isclicked(4, 4));
		assertEquals(true,oval.isclicked(6, 2));
		assertEquals(true,oval.isclicked(6, 3));
		assertEquals(true,oval.isclicked(6, 4));
		assertEquals(true,oval.isclicked(6, 5));
		assertEquals(true,oval.isclicked(6, 6));
	}
	@Test
	void testdeplacerOval() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
	
		oval.Deplacer(10, 4);
		assertEquals(10, oval.GetX());
		assertEquals(4, oval.GetY());
	}
	@Test
	void testCompare() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		Oval ovalv=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		
		assertEquals(0, ovalv.compareTo(oval));
	}


}
