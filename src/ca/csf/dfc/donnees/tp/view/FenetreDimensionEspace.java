/**
 * 
 */
package ca.csf.dfc.donnees.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Fenêtre qui permet à un utilisateur d'entrer les dimensions d'un espace de travail.
 * @author JBrazeau
 *
 */
public class FenetreDimensionEspace extends JDialog {
	private static final long serialVersionUID = 1L;
	
	// Éléments du dialogue
	private JLabel     lbl_Introductif = new JLabel("Entrez les paramètres suivants: (en pixel)");
	private JLabel     lbl_MessErreur  = new JLabel("");
	private JLabel     lbl_Hauteur     = new JLabel("Hauteur :");
	private JTextField txt_Hauteur     = new JTextField();
	private JLabel     lbl_Largeur     = new JLabel("Largeur :");
	private JTextField txt_Largeur     = new JTextField();
	private JButton    btn_OK          = new JButton("OK");
	private JButton    btn_Cancel      = new JButton("Cancel");
	
	// Choix et réponse utilisateur
	private boolean m_etatDialogue   = false;
	private Integer m_largeurChoisie = null;
	private Integer m_hauteurChoisie = null;
	
	public FenetreDimensionEspace(JFrame p_Parent) { 
		super(p_Parent, "Dimension d'espace", true);
		
		// Configuration des éléments graphiques
		this.txt_Hauteur.setPreferredSize(new Dimension(45,30));
		this.txt_Hauteur.setDocument(new JTextFieldLimite(4));
		
		this.txt_Largeur.setPreferredSize(new Dimension(45,30));
		this.txt_Largeur.setDocument(new JTextFieldLimite(4));
		
		this.lbl_Introductif.setFont(new Font("sans-serif", Font.PLAIN, 15));
		this.lbl_Introductif.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
		this.lbl_Introductif.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.lbl_MessErreur.setFont(new Font("sans-serif", Font.PLAIN, 12));
		this.lbl_MessErreur.setForeground(Color.RED);
		this.lbl_MessErreur.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// NORD
		JPanel pannNord = new JPanel();
		BoxLayout boxLayoutNord = new BoxLayout(pannNord, BoxLayout.Y_AXIS);
			pannNord.setLayout(boxLayoutNord);
			pannNord.setAlignmentX(Component.CENTER_ALIGNMENT);
			pannNord.add(this.lbl_Introductif, BorderLayout.NORTH);
			pannNord.add(this.lbl_MessErreur, BorderLayout.NORTH);
		this.add(pannNord, BorderLayout.NORTH);
		
		// CENTRE 
		JPanel pannCentre = new JPanel(new FlowLayout());
			pannCentre.add(this.lbl_Hauteur);
			pannCentre.add(this.txt_Hauteur);
			pannCentre.add(this.lbl_Largeur);
			pannCentre.add(this.txt_Largeur);
		this.add(pannCentre, BorderLayout.CENTER);
		
		// SUD
		JPanel pannSud = new JPanel(new FlowLayout());
			pannSud.add(btn_OK);
			pannSud.add(btn_Cancel);
		this.add(pannSud, BorderLayout.SOUTH);
		
		//
		this.setSize(330,175);
		this.setLocationRelativeTo(p_Parent);
		
		// Gestion boutons
		btn_Cancel.addActionListener(new GestCancel());
		btn_OK.addActionListener(new GestOK());
		
	}
	
	/**
	 * Retourne un booléen définissant si le dialogue a résulté dans les mesures attendues
	 * @return l'état du dialogue
	 */
	public boolean getResultatDialogue() {
		return this.m_etatDialogue;
	}
	
	/**
	 * Retourne la hauteur choisie lors du dialogue.
	 * @return La hauteur choisie.
	 */
	public Integer getHauteurChoisie() {
		return this.m_hauteurChoisie;
	}
	
	/**
	 * Retourne la largeur choisie lors du dialogue.
	 * @return La largeur choisie.
	 */
	public Integer getLargeurChoisie() {
		return this.m_largeurChoisie;
	}
	
	/**
	 * Classe interne qui gère les actions du bouton Cancel.
	 * @author JBrazeau
	 */
	public class GestCancel implements ActionListener {
		/**
		 * Redéfinition
		 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			FenetreDimensionEspace.this.m_largeurChoisie = null;
			FenetreDimensionEspace.this.m_hauteurChoisie = null;
			FenetreDimensionEspace.this.m_etatDialogue = false;
			
			FenetreDimensionEspace.this.setVisible(false);
		}
	}
	
	/**
	 * Classe interne qui gère les actions du bouton OK.
	 * @author JBrazeau
	 */
	public class GestOK implements ActionListener {
		
		/**
		 * Redéfinition
		 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			String messageErreur = null;
			
			try 
			{
				FenetreDimensionEspace.this.m_hauteurChoisie = Integer.parseInt(FenetreDimensionEspace.this.txt_Hauteur.getText());
				FenetreDimensionEspace.this.m_largeurChoisie = Integer.parseInt(FenetreDimensionEspace.this.txt_Largeur.getText());
				
				if(    FenetreDimensionEspace.this.m_hauteurChoisie > 0 
					&& FenetreDimensionEspace.this.m_largeurChoisie > 0) 
				{
					FenetreDimensionEspace.this.m_etatDialogue = true;
				}
				else {
					messageErreur = "Les paramètres doivent être supérieur à zéro.";
					FenetreDimensionEspace.this.m_etatDialogue = false;
				}
			}
			catch(NumberFormatException ex) 
			{
				System.err.println(ex);
				
				messageErreur = "Les paramètres doivent être numérique.";
				FenetreDimensionEspace.this.m_etatDialogue = false;
			}
			finally 
			{
			
				if(FenetreDimensionEspace.this.m_etatDialogue) 
				{	
					FenetreDimensionEspace.this.setVisible(false);
					
				}
				else 
				{
					FenetreDimensionEspace.this.lbl_MessErreur.setText(messageErreur);
				}
			}
		}
	}

	/**
	 * Classe interne qui sert à limiter le nombre de caractères d'un JTextField
	 * @author JBrazeau
	 *
	 */
	public class JTextFieldLimite extends PlainDocument {
		private static final long serialVersionUID = 1L;  
		/**
		 * Nombre limite de caractères
		 */
		private int m_limite;
		
		/**
		 * Initialise un JTextFieldLimite avec la limite.
		 * @param p_limite La limite.
		 */
		JTextFieldLimite(int p_limite) 
		{
		   super();
		   this.m_limite = p_limite;
		}
		
		/**
		 * Gère l'insertion des caractères pour qu'elle respecte la limite.
		 */
		 public void insertString( int p_offset, String  p_str, AttributeSet p_attr ) throws BadLocationException {
		    if (p_str == null) return;

		    if ((getLength() + p_str.length()) <= m_limite) {
		      super.insertString(p_offset, p_str, p_attr);
		    }
		  }
		}
}
