package ca.csf.dfc.donnees.tp.main;

import ca.csf.dfc.donnees.tp.controller.*;
import ca.csf.dfc.donnees.tp.model.EspaceTravail;
import ca.csf.dfc.donnees.tp.view.*;

public class Main {

	public static void main(String[] args) {
		EspaceTravail esp = new EspaceTravail(200,200);
		esp.draw(new Oval(12,12,45,32,1,Color.BLUE, Color.RED));
		esp.draw(new Rectangle(12,32,1,3,1,Color.ORANGE, Color.BLACK));
		
		EnregistrementXML.getInstance().Enregistrer(esp);
		
		esp = new EspaceTravail(400,400);
		esp.draw(new Rectangle(1,1,13,43,4,Color.CYAN, Color.GRAY));
		
		EnregistrementXML.getInstance().Charger(esp);
		EnregistrementXML.getInstance().Enregistrer(esp);
		
	} 
}
