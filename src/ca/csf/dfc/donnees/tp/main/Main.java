package ca.csf.dfc.donnees.tp.main;

import ca.csf.dfc.donnees.tp.controller.*;
import ca.csf.dfc.donnees.tp.model.EspaceTravail;
import ca.csf.dfc.donnees.tp.view.*;

import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		EspaceTravail esp = new EspaceTravail(200, 200);
		esp.draw(new Oval(50,50,100,50,4,Color.BLUE, Color.BLACK));
		esp.draw(new Rectangle(100,100,40,40,4, Color.DARK_GRAY, Color.RED));
		
		EnregistrementXML enrXML = EnregistrementXML.getInstance();
		
		enrXML.Enregistrer(esp);
		ExporteurSVG.getInstance().Exporter(esp);
	}
}
