package ca.csf.dfc.donnees.tp.controller;


import java.awt.Color;

//import org.omg.IOP.TAG_INTERNET_IOP;

/**
 * 
 */

/**
 * @author administrateur
 *
 */
public class Oval extends Forme {
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

	@Override
	public Boolean isclicked(int p_X, int p_Y) {

		int centreX = this.m_X / 2;
		int centreY = this.m_Y / 2;
		int demiH = this.m_Hauteur / 2;
		int demiL = this.m_Largeur / 2;

		return ((int) Math.pow((p_X - centreX), 2) / (int) Math.pow(demiH, 2))
				+ ((int) Math.pow((p_Y - centreY), 2)) / ((int) Math.pow(demiL, 2)) <= 1;
	}

	@Override
	public void Deplacer(int p_x, int p_y) {
		this.setX(p_x);
		this.setY(p_y);

	}

	@Override
	public void Modifier(int p_Largeur, int p_Hauteur) {
		this.setLargeur(p_Largeur);
		this.setHauteur(p_Hauteur);

	}

	@Override
	public String GetForme() {

		return "Oval";
	}

	@Override
	public int GetX() {

		return this.m_X;
	}

	@Override
	public int GetY() {

		return this.m_Y;
	}

	@Override
	public int GetHauteur() {

		return this.m_Hauteur;
	}

	@Override
	public int GetLargeur() {

		return this.m_Largeur;
	}

	@Override
	public int GetTrait() {
		return this.m_Trait;
	}

	@Override
	public Color GetCouleur() {

		return this.m_Couleur;
	}

	@Override
	public Color GetRemplissage() {

		return this.m_Remplissage;
	}

	@Override
	public int compareTo(IForme p_o) {
		int resultat=-1;
		if (this.m_Y==p_o.GetX()&&
				this.m_Y==p_o.GetY()&&
				this.m_Hauteur==p_o.GetHauteur()&&
				this.m_Largeur==p_o.GetLargeur()&&
				this.m_Couleur==p_o.GetCouleur()&&
				this.m_Remplissage==p_o.GetRemplissage()&&
				this.m_Trait==p_o.GetTrait()&&
				this.GetForme()==p_o.GetForme())
		{
			resultat=0;
		}
		return resultat;
	}

	@Override
	protected void setX(int p_X) {
		this.m_X = p_X;

	}

	@Override
	protected void setY(int p_Y) {
		this.m_Y = p_Y;

	}

	@Override
	protected void setHauteur(int p_Hauteur) {
		this.m_Hauteur = p_Hauteur;

	}

	@Override
	protected void setLargeur(int p_Largeur) {
		this.m_Largeur = p_Largeur;

	}

	@Override
	protected void setTrait(int p_Trait) {
		this.m_Trait = p_Trait;

	}

	@Override
	protected void setCouleur(Color p_Couleur) {
		this.m_Couleur = p_Couleur;

	}

	@Override
	protected void setRemplissage(Color p_Remplissage) {
		this.m_Remplissage = p_Remplissage;

	}

}
