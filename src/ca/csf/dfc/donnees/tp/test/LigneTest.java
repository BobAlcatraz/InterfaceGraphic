package ca.csf.dfc.donnees.tp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.dfc.donnees.tp.controller.Ligne;

class LigneTest {

	@Test
	void testGetSetX() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		assertEquals(test.GetX(), 1);		
		test.Deplacer(3, 0);
		assertEquals(test.GetX(), 3);
		
	}

	@Test
	void testGetSetY() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		assertEquals(test.GetY(), 1);		
		test.Deplacer(0,3);
		assertEquals(test.GetY(), 3);
		
	}

	@Test
	void testGetSetHauteur() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		assertEquals(test.GetHauteur(), 1);		
		test.Modifier(0,130);
		assertEquals(test.GetHauteur(), 130);
		
	}

	@Test
	void testGetSetLargeur() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		assertEquals(test.GetLargeur(), 1);		
		test.Modifier(130,0);
		assertEquals(test.GetLargeur(), 130);
	}

	@Test
	void testGetSetTrait() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		assertEquals(test.GetTrait(), 1);		
		test.ModifierTrait(3);
		assertEquals(test.GetTrait(), 3);
	}

	@Test
	void testGetSetCouleur() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		assertEquals(test.GetCouleur(), null);
		test.ModifierCouleur(Color.BLACK);
		assertEquals(test.GetCouleur(), Color.BLACK);
		test.ModifierCouleur(Color.WHITE);
		assertEquals(test.GetCouleur(), Color.WHITE);

		
	}

	@Test
	void testGetSetRemplissage() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);
		
		assertEquals(test.GetRemplissage(), null);
		test.ModifierRemplisage(Color.BLACK);
		assertEquals(test.GetRemplissage(), Color.BLACK);
		test.ModifierRemplisage(Color.WHITE);
		assertEquals(test.GetRemplissage(), Color.WHITE);
	}

	@Test
	void testLigne() {
		Ligne test = new Ligne(1, 2, 3, 4, 5, Color.BLACK, Color.WHITE);
		
		assertEquals(test.GetX(), 1);
		assertEquals(test.GetY(), 2);
		assertEquals(test.GetHauteur(), 3);
		assertEquals(test.GetLargeur(), 4);
		assertEquals(test.GetTrait(), 5);
		
		assertEquals(test.GetCouleur(), Color.BLACK);
		assertEquals(test.GetRemplissage(), Color.WHITE);
				
	}

	@Test
	void testIsclicked() {
		Ligne test = new Ligne(10, 92, 24, 30, 2, null, null);	
		
		assertTrue(test.isclicked(15, 88));
		assertTrue(test.isclicked(35, 72));	
		assertTrue(test.isclicked(25, 82));
		assertTrue(test.isclicked(26, 76));	
		
		assertFalse(test.isclicked(37, 108));	
		assertFalse(test.isclicked(-5, 68));
		assertFalse(test.isclicked(1000, 1000));
	}

	@Test
	void testDeplacer() {
		Ligne test = new Ligne(1, 2, 1, 1, 1, null, null);	
		
		assertEquals(test.GetX(), 1);
		assertEquals(test.GetY(), 2);
		
		test.Deplacer(73, 113);
		
		assertEquals(test.GetX(), 73);
		assertEquals(test.GetY(), 113);	
		
	}

	@Test
	void testModifier() {
		Ligne test = new Ligne(1, 1, 4, 5, 1, null, null);	
		
		assertEquals(test.GetHauteur(), 4);
		assertEquals(test.GetLargeur(), 5);
				
		test.Modifier(113, 73);
		
		assertEquals(test.GetHauteur(), 73);
		assertEquals(test.GetLargeur(), 113);
	}

	@Test
	void testCompareTo() {
		Ligne test = new Ligne(1, 1, 1, 1, 1, null, null);	
		
		
	}

}
