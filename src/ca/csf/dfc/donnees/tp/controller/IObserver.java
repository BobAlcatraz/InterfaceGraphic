package ca.csf.dfc.donnees.tp.controller;

import ca.csf.dfc.donnees.tp.model.IEspaceTravail;

public interface IObserver {
	/**
	 * Compare l'espace de travail en mémoire avec un autre espace
	 * de travail
	 * @param 	p_EspaceTravail		Espace de travail à comparer
	 * @return 	true si les espaces de travail sont identiques
	 */
	boolean Comparer(IEspaceTravail p_EspaceTravail);
	/**
	 * Met en mémoire un espace de travail
	 * @param 	p_EspaceTravail		Espace de travail à mettre en mémoire
	 */
	public void SetEnregistrement(IEspaceTravail p_EspaceTravail);
}