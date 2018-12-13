package ca.csf.dfc.donnees.tp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.dfc.donnees.tp.controller.Rectangle;

class TestRectangle {

	@Test
	void testSetX() {
		Rectangle rect = new Rectangle(2, 10, 4, 6, 8, Color.BLACK, Color.GRAY);

		assertEquals(2, rect.GetX());
		rect.Deplacer(8, 12);
		assertEquals(8, rect.GetX());
	}

	@Test
	void testSetY() {
		Rectangle rect = new Rectangle(10, 8, 6, 7, 10, null, null);
		assertEquals(8, rect.GetY());
		rect.Deplacer(8, 10);
		assertEquals(10, rect.GetY());
	}

	@Test
	void testIsClicked() {
		
		Rectangle rect = new Rectangle(2, 2, 4, 5, 10, null, null);
		assertTrue(rect.isclicked(3, 4));
		assertTrue(rect.isclicked(4, 3));
		assertTrue(rect.isclicked(2, 2));
		assertTrue(rect.isclicked(7, 2));
		assertTrue(rect.isclicked(2	, 6));
		assertTrue(rect.isclicked(7,6));

	}

	@Test
	void testIsNotClicked() {
		Rectangle rect = new Rectangle(2, 2, 4, 5, 10, null, null);
		assertFalse(rect.isclicked(3, 7));
		assertFalse(rect.isclicked(3, 9));
		assertFalse(rect.isclicked(5, 0));
		assertFalse(rect.isclicked(1, 1));
		assertFalse(rect.isclicked(9, 8));
		assertFalse(rect.isclicked(8, 1));
	}

	@Test
	void testDeplacerRectangle() {
		Rectangle rect = new Rectangle(2, 8, 6, 10, 10, null, null);
		rect.Deplacer(8, 6);
		assertEquals(8, rect.GetX());
		assertEquals(6, rect.GetY());
	}

	@Test
	void testModifierRectangle() {
		Rectangle rect = new Rectangle(8, 7, 3, 5, 10, Color.green, Color.blue);
		assertEquals(3, rect.GetHauteur());
		assertEquals(5, rect.GetLargeur());

		rect.Modifier(10, 6);

		assertEquals(6, rect.GetHauteur());
		assertEquals(10, rect.GetLargeur());

	}

	@Test
	void testCompareRectangle() {
		Rectangle rect = new Rectangle(8, 7, 3, 5, 10, Color.green, Color.blue);
		Rectangle rect2 = new Rectangle(8, 7, 3, 5, 10, Color.green, Color.blue);

		assertEquals(0, rect.compareTo(rect2));
	}
}
