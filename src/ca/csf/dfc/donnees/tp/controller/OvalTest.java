package ca.csf.dfc.donnees.tp.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class OvalTest {

	@Test
	void testConst1() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		assertEquals(7, oval.m_X);
	}
	@Test
	void testConst2() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		assertEquals(Color.BLACK, oval.m_Couleur);
	}
	
	@Test
	void testIsclicked()
	{
		Oval oval=new Oval(2,8 ,4, 8, 4, Color.BLACK, Color.BLUE);
		//Point m=new Point(8, 4);
		assertEquals(true,oval.isclicked(8, 6));
	}
	@Test
	void testdeplacerOval() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		oval.setX(10);
		oval.setY(4);
		
		assertEquals(10, oval.m_X);
		assertEquals(4, oval.m_Y);
	}
	@Test
	void testCompare() {
		Oval oval=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		Oval ovalv=new Oval(7, 6,10, 10, 8, Color.BLACK, Color.BLUE);
		
		assertEquals(0, ovalv.compareTo(oval));
	}

}
