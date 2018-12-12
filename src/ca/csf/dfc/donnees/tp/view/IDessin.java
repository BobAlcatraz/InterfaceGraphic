package ca.csf.dfc.donnees.tp.view;

import ca.csf.dfc.donnees.tp.controller.IForme;

public interface IDessin {
	/**
	 * Permet de créer un nouvel espace de travail
	 * @param	p_Largeur	Largeur du nouvel espace de travail
	 * @param	p_Hauteur	Hauteur du nouvel espace de travail
	 */
	public void CreerEspaceTravail(int p_Largeur, int p_Hauteur);
	/**
	 * Verifie si l'espace de travail courant est le même que l'espace de travail du
	 * dernier enregistrement
	 * @return	true si les enregistrements sont identiques
	 */
	public boolean VerifierModification();
	/**
	 * Fait une sauvegarde de l'espace de travail courant
	 * @return	true si l'enregistrement s'est bien déroulé
	 */
	public boolean Sauvegarder();
	/**
	 * Charge un espace de travail précédemment enregistré
	 * @return  true si le chargement s'est bien déroulé
	 */
	public boolean Charger();
	/**
	 * Exporte un espace de travail en format visualisable
	 * @return 	true si l'exportation s'est bien déroulé
	 */
	public boolean Exporter();
	/**
	 * Supprime la forme sélectionnée s'il y en a une
	 */
	public void Supprimer();
	/**
	 * Ajoute une forme dans l'espace de travail
	 * @param 	p_Forme		Forme à ajouter à l'espace de travail
	 */
	public void AjouterForme(IForme p_Forme);
}
