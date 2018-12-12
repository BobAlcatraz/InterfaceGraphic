package ca.csf.dfc.donnees.tp.controller;

import java.awt.*;

//Interface définissant les méthodes d'une forme.
public interface IForme extends Comparable<IForme>{

	/**
	 * Permet de savoir si les coordonnées d'un point cliqué fait partie de la zone
	 * occupée par la forme.
	 * @param p_X Distance 'x' de la coordonnée du point cliqué.
	 * @param p_Y Distance 'y' de la coordonnée du point cliqué.
	 * @return Vrai ou faux, dépendant si la forme est cliquée ou non.
	 */
	public Boolean isclicked(int p_X, int p_Y);	
	
	/**
	 * Déplace la forme dans l'espace de travail en lui donnant une nouvelle coordonnée.
	 * @param p_X Nouvelle distance 'x' de la forme.
	 * @param p_Y Nouvelle distance 'y' de la forme.
	 */
	public void Deplacer(int p_X, int p_Y);
	
	/**
	 * Modifie la forme.
	 * @param p_Largeur Nouvelle largeur de la forme.
	 * @param p_Hauteur Nouvelle hauteur de la forme.
	 */
	public void Modifier(int p_Largeur, int p_Hauteur);
	
	/**
	 * Permet de connaitre le nom de la forme.
	 * @return Le nom de la forme.
	 */
	public String GetForme();
	
	/**
	 * Retourne une copie de la forme.
	 * @return Une copie de la forme.
	 */
	public IForme GetCopie();
	
	/**
	 * Retourne la distance 'x' de la coordonnée de la forme.
	 * @return la distance 'x' de la coordonnée de la forme.
	 */
	public int GetX();
	
	/**
	 * Retourne la distance 'y' de la coordonnée de la forme.
	 * @return la distance 'Y' de la coordonnée de la forme.
	 */
	public int GetY();
	
	/**
	 * Retourne la hauteur de la forme
	 * @return la hauteur de la forme.
	 */
	public int GetHauteur();
	
	/**
	 * Retourne la largeur de la forme.
	 * @return la largeur de la forme.
	 */
	public int GetLargeur();
	
	/**
	 * Retourne la largeur du trait de la forme.
	 * @return la largeur du trait de la forme.
	 */
	public int GetTrait();
	
	/**
	 * Retourne la couleur de la forme.
	 * @return la couleur de la forme.
	 */
	public Color GetCouleur();
	
	/**
	 * Retourne la couleur de remplissage de la forme.
	 * @return la couleur de remplissage de la forme.
	 */
	public Color GetRemplissage();
	
	/**
	 * Modifie la couleur de la forme.
	 * @param La nouvelle couleur de la forme.
	 */
	public void ModifierCouleur(Color p_couleur);
	
	/**
	 * Modifie le trait de la forme.
	 * @param p_trait La nouvelle largeur du trait de la forme.
	 */
	public void ModifierTrait(int p_trait);
	
	/**
	 * Modifie la couleur de remplissage de la forme.
	 * @param p_couleur La nouvelle couleur de remplissage de la forme.
	 */
	public void ModifierRemplissage(Color p_couleur);
	
	
	
}
