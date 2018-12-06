package ca.csf.dfc.donnees.tp.main;

import ca.csf.dfc.donnees.tp.model.EspaceTravail;
import ca.csf.dfc.donnees.tp.view.*;

public class Main {

	public static void main(String[] args) {
		new Dessin().setVisible(true);
		EspaceTravail test = new EspaceTravail(500, 500);
		System.out.println(test.getHauteur());
		System.out.println(test.getLargeur());
	}
}
