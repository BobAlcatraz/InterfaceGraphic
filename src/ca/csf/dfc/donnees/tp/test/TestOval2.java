package ca.csf.dfc.donnees.tp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		Oval oval=new Oval(2,8 ,4, 8, 4, Color.BLACK, Color.BLUE);
		
		assertTrue(oval.isclicked(3,9));
		assertTrue(oval.isclicked(4,9));
		assertTrue(oval.isclicked(5,9));
		assertTrue(oval.isclicked(6,9));
	}
	@Test
	void isNotCliched() {
		Oval oval=new Oval(2,8,4,8,9,Color.CYAN,Color.darkGray);
		
		assertEquals(false,oval.isclicked(10, 2));
		assertEquals(false,oval.isclicked(12, 10));
		assertEquals(false,oval.isclicked(2, 2));
		assertEquals(false,oval.isclicked(1, 1));
		
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
	@Test
	void testSetX() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		assertEquals(oval.GetX(),7);
		oval.Deplacer(8, 10);
		assertEquals(8,oval.GetX());
	}
	@Test
	void testModifierCouleur() {
		Oval oval=new Oval(10,5,7,9,2,Color.PINK,Color.CYAN);
		assertEquals(Color.PINK,oval.GetCouleur());
		oval.ModifierCouleur(Color.red);
		
		assertEquals(Color.red,oval.GetCouleur());
		
	}
	@Test
	void testModifierTrait()
	{
		Oval oval=new Oval(10,5,7,9,2,Color.PINK,Color.CYAN);
		assertEquals(2,oval.GetTrait());
		
		oval.ModifierTrait(8);
		
		assertEquals(8,oval.GetTrait());
		
		
	}


}
