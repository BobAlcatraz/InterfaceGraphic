package ca.csf.dfc.donnees.tp.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.csf.dfc.donnees.tp.controller.*;
import ca.csf.dfc.donnees.tp.model.*;

public class Dessin extends JFrame implements IDessin{
	private static final long serialVersionUID = 1L;
	private IEspaceTravail m_EspaceTravail;
	private IObserver m_Observer;
	private IEnregistrement m_Enregistrement;
	private IForme m_FormeSelection = null;
	
	public Dessin() {
		super("Dessin Vectoriel");
		this.m_EspaceTravail = new EspaceTravail(500, 500);
		this.setSize(530, 700);
		this.m_Observer = new Observer();
		this.m_Enregistrement = new EnregistrementXML();
		//Initialisation des aspects visuels
		this.setLayout(new BorderLayout());
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(3,1));
		buttons.add(new JButton("Rectangle"));
		buttons.add(new JButton("Oval"));
		buttons.add(new JButton("Ligne"));
		this.add(buttons, BorderLayout.WEST);
		this.add((EspaceTravail)this.m_EspaceTravail, BorderLayout.CENTER);
		//Events
		this.m_EspaceTravail.ajouterMouseMotionListener(new Crayon());
	}
	
	@Override
	public void CreerEspaceTravail(int p_Largeur, int p_Hauteur) {
		if (VerifierModification()) {
			this.m_EspaceTravail = new EspaceTravail(p_Largeur, p_Hauteur);
		}
		else {
			
		}
	}
	
	@Override
	public boolean VerifierModification() {
		return this.m_Observer.Comparer(this.m_EspaceTravail);
	}

	@Override
	public boolean Sauvegarder() {
		boolean confirm = true;
		try {
			this.m_Enregistrement.Enregistrer(this.m_EspaceTravail);
		}
		catch (Exception e){
			confirm = false;
		}
		return confirm;
	}

	@Override
	public boolean Charger() {
		boolean confirm = true;
		JFileChooser open = new JFileChooser();
		open.showOpenDialog(this);
		if (open.getSelectedFile() != null) {
			if (VerifierModification()) {
				try {
					this.m_EspaceTravail = this.m_Enregistrement.Charger();
				}
				catch (Exception e) {
					confirm = false;
				}
			}
			else {
				confirm = false;
			}
		}
		return confirm;
	}

	@Override
	public boolean Exporter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void AjouterForme() {
		this.m_EspaceTravail.draw(this.m_FormeSelection);		
	}
	
	private class Crayon implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent p_e) {
			//TODO
		}

		@Override
		public void mouseMoved(MouseEvent p_e) {}
		
	}
}
