package ca.csf.dfc.donnees.tp.main;

import java.awt.Color;

import javax.swing.JFrame;

import ca.csf.dfc.donnees.tp.controller.*;
import ca.csf.dfc.donnees.tp.model.EspaceTravail;
import ca.csf.dfc.donnees.tp.view.*;

public class Main {

	public static void main(String[] args) {
		FenetreDimensionEspace creat = new FenetreDimensionEspace(new JFrame());
		
		creat.setVisible(true);
		
		System.out.println(creat.getResultatDialogue());
		if(creat.getResultatDialogue()) {
			System.out.println(creat.getHauteurChoisie());
			System.out.println(creat.getLargeurChoisie());
		}
	} 
}
