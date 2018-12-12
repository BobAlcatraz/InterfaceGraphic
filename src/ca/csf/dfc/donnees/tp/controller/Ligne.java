package ca.csf.dfc.donnees.tp.controller;

import java.awt.Color;

/**
 * Classe représentant une ligne 
 */
public class Ligne extends Forme{

	
	/**
	 * Constructeur d'initialisation
	 * @param p_X La coordonnée 'x' du premier point de la ligne
	 * @param p_Y La coordonnée 'y' du premier point de la ligne
	 * @param p_Hauteur La hauteur de la ligne
	 * @param p_Largeur La largeur de la ligne
	 * @param p_Trait La grosseur de trait de la ligne
	 * @param p_Couleur La couleur de la ligne
	 * @param p_Remplissage La couleur de remplissage de la ligne (Même que p_Couleur)
	 */
	public Ligne(int p_X, int p_Y, int p_Hauteur, int p_Largeur, int p_Trait, Color p_Couleur, Color p_Remplissage) {
		
		this.m_X = p_X;
		this.m_Y = p_Y;
		this.m_Hauteur = p_Hauteur;
		this.m_Largeur = p_Largeur;
		this.m_Trait = p_Trait;
		this.m_Couleur = p_Couleur;
		this.m_Remplissage = p_Remplissage;
		
	}
	
	/**
	 * Constructeur par copie.
	 * @param p_Ligne La ligne à copier.
	 */
	public Ligne(IForme p_Ligne) {
		this.m_X = p_Ligne.GetX();
		this.m_Y = p_Ligne.GetY();
		this.m_Hauteur = p_Ligne.GetHauteur();
		this.m_Largeur = p_Ligne.GetLargeur();
		this.m_Trait = p_Ligne.GetTrait();
		this.m_Couleur = p_Ligne.GetCouleur();
		this.m_Remplissage = p_Ligne.GetRemplissage();
	}
	
	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#isclicked(int, int)
	 */
	@Override
	public Boolean isclicked(int p_X, int p_Y) {
				
		int x1 = this.m_X;
		int y1 = this.m_Y;
		int x2 = (x1 + this.m_Largeur);
		int y2 = (y1 + this.m_Hauteur);
			
		Boolean retour = false;		
		
		double a = ((y2-y1)/(x2-x1));		
		double b = -((a*x2)-y2);
	
		
		
		if((y1 == y2) && ((p_Y >= (y1 - 20)) && (p_Y <= (y1 + 20)))) {
			
			if(((p_X >= (x1 - 20)) && (p_X <= (x2 + 20)))) {
				retour = true;
			}
			
		}
		else if((x1 == x2) && ((p_X >= (x1 - 20)) && (p_X <= (x1 + 20)))) {
			
			if(((p_Y >= (y1 - 20)) && (p_Y <= (y2 + 20)))) {
				retour = true;
			}
						
		}
		else if ((this.m_Hauteur > 0) && ((p_X >= x1)&&(p_X <= x2))&&((p_Y <= y2)&&(p_Y >= y1))) {
			
			if((p_Y >= ((a * p_X + b) - 70)) && (p_Y <= ((a * p_X + b) + 70))) {
				retour = true;
			}
			
		}
		else if ((this.m_Hauteur < 0) && ((p_X >= x1)&&(p_X <= x2))&&((p_Y >= y2)&&(p_Y <= y1))) {
			
			if((p_Y >= ((a * p_X + b) - 70)) && (p_Y <= ((a * p_X + b) + 70))) {
				retour = true;
			}
			
		}
			
		return retour;
		
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#Deplacer(int, int)
	 */
	@Override
	public void Deplacer(int p_X, int p_Y) {
		this.setX(p_X);
		this.setY(p_Y);		
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#Modifier(int, int)
	 */
	@Override
	public void Modifier(int p_Largeur, int p_Hauteur) {
		this.setLargeur(p_Largeur);
		this.setHauteur(p_Hauteur);	
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetForme()
	 */
	@Override
	public String GetForme() {
		return "ligne";
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetX()
	 */
	@Override
	public int GetX() {
		return this.m_X;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetY()
	 */
	@Override
	public int GetY() {
		return this.m_Y;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetHauteur()
	 */
	@Override
	public int GetHauteur() {
		return this.m_Hauteur;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetLargeur()
	 */
	@Override
	public int GetLargeur() {
		return this.m_Largeur;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetTrait()
	 */
	@Override
	public int GetTrait() {
		return this.m_Trait;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetCouleur()
	 */
	@Override
	public Color GetCouleur() {
		return this.m_Couleur;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetRemplissage()
	 */
	@Override
	public Color GetRemplissage() {
		return this.m_Remplissage;
	}

	/**
	 * Compare la ligne à une autre forme afin de savoir si elle est pareille
	 * ou différente à cette forme comparée.
	 * @param IForme o Forme à comparer.
	 */
	@Override
	public int compareTo(IForme o) {
		int retour = -1;
		
		if ((this.m_X == o.GetX()) && (this.m_Y == o.GetY()) &&
				(this.m_Hauteur == o.GetHauteur()) && (this.m_Largeur == o.GetLargeur()) &&
				(this.m_Trait == o.GetTrait()) && (this.m_Couleur == o.GetCouleur()) &&
				(this.m_Remplissage == o.GetRemplissage()) && (this.GetForme() == o.GetForme())) {	
			
			retour = 0;
		}		
		return retour;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setX(int)
	 */
	@Override
	protected void setX(int p_X) {
		this.m_X = p_X;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setY(int)
	 */
	@Override
	protected void setY(int p_Y) {
		this.m_Y = p_Y;	
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setHauteur(int)
	 */
	@Override
	protected void setHauteur(int p_Hauteur) {
		this.m_Hauteur = p_Hauteur;	
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setLargeur(int)
	 */
	@Override
	protected void setLargeur(int p_Largeur) {
		this.m_Largeur = p_Largeur;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setTrait(int)
	 */
	@Override
	protected void setTrait(int p_Trait) {
		this.m_Trait = p_Trait;
		
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setCouleur(Color)
	 */
	@Override
	protected void setCouleur(Color p_Couleur) {
		this.m_Couleur = p_Couleur;	
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.Forme#setRemplissage(Color)
	 */
	@Override
	protected void setRemplissage(Color p_Remplissage) {
		this.m_Remplissage = p_Remplissage;
		
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetCopie()
	 */
	@Override
	public IForme GetCopie() {
		return new Ligne(this);
	}
  
	
	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#ModifierCouleur(Color)
	 */
	@Override
	public void ModifierCouleur(Color p_couleur) {
		this.m_Couleur = p_couleur;
		
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#ModifierTrait(int)
	 */
	@Override
	public void ModifierTrait(int p_trait) {
		this.m_Trait = p_trait;
		
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#ModifierRemplissage(Color)
	 */
	@Override
	public void ModifierRemplissage(Color p_couleur) {
		this.m_Remplissage = p_couleur;
	}
}
