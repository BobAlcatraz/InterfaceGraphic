package ca.csf.dfc.donnees.tp.controller;

import java.awt.Color;

//import org.omg.IOP.TAG_INTERNET_IOP;

/**
 * Classe représentant un oval
 */
public class Oval extends Forme {
	
	
	/**
	 * Constructeur d'initialisation
	 * @param p_X La coordonnée 'x' du premier point de l'oval
	 * @param p_Y La coordonnée 'y' du premier point de l'oval
	 * @param p_Hauteur La hauteur de l'oval
	 * @param p_Largeur La largeur de l'oval
	 * @param p_Trait La grosseur de trait de l'oval
	 * @param p_Couleur La couleur de la l'oval
	 * @param p_Remplissage La couleur de remplissage de l'oval
	 */
	public Oval(int p_X, int p_Y, int p_Hauteur, 
			int p_Largeur, int p_Trait,
			Color p_Couleur, Color p_Remplossage) {
		this.m_X=p_X;
		this.m_Y=p_Y;
		this.m_Hauteur=p_Hauteur;
		this.m_Largeur=p_Largeur;
		this.m_Trait=p_Trait;
		this.m_Couleur=p_Couleur;
		this.m_Remplissage=p_Remplossage;

	}
	
	/**
	 * Constructeur par copie.
	 * @param p_Oval L'oval à copier.
	 */
	public Oval(IForme p_Oval) {
		this.m_X = p_Oval.GetX();
		this.m_Y = p_Oval.GetY();
		this.m_Hauteur = p_Oval.GetHauteur();
		this.m_Largeur = p_Oval.GetLargeur();
		this.m_Trait = p_Oval.GetTrait();
		this.m_Couleur = p_Oval.GetCouleur();
		this.m_Remplissage = p_Oval.GetRemplissage();
	}

	
	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#isclicked(int, int)
	 */
	@Override
	public Boolean isclicked(int p_X, int p_Y) {

		int demiH = this.m_Hauteur / 2;
		int demiL = this.m_Largeur / 2;
		int centreX = this.m_X +demiL;
		int centreY = this.m_Y +demiH;
		

		return ((int) Math.pow((p_X - centreX), 2) / (int) Math.pow(demiH, 2))
				+ ((int) Math.pow((p_Y - centreY), 2)) / ((int) Math.pow(demiL, 2)) <= 1;
	}

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#Deplacer(int, int)
	 */
	@Override
	public void Deplacer(int p_x, int p_y) {
		this.setX(p_x);
		this.setY(p_y);

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

		return "oval";
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
	 * Compare l'oval à une autre forme afin de savoir s'il est pareil
	 * ou différent à cette forme comparée.
	 * @param IForme o Forme à comparer.
	 */
	@Override
	public int compareTo(IForme p_o) {
		int resultat=-1;
		if (this.m_X==p_o.GetX()&&
				this.m_Y==p_o.GetY()&&
				this.m_Hauteur==p_o.GetHauteur()&&
				this.m_Largeur==p_o.GetLargeur()&&
				this.m_Couleur==p_o.GetCouleur()&&
				this.m_Remplissage==p_o.GetRemplissage()&&
				this.m_Trait==p_o.GetTrait()&&
				this.GetForme()==p_o.GetForme()
				)
		{
			resultat=0;
		}
		
		
		return resultat;
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

	/**
	 * Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IForme#GetCopie()
	 */
	@Override
	public IForme GetCopie() {
		return new Oval(this);
	}


}
