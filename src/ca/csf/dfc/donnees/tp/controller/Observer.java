package ca.csf.dfc.donnees.tp.controller;

import ca.csf.dfc.donnees.tp.model.IEspaceTravail;

public class Observer implements IObserver{
	private IEspaceTravail m_Enregistrement;
	
	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public boolean Comparer(IEspaceTravail p_EspaceTravail) {
		return this.m_Enregistrement.equals(p_EspaceTravail);
	}

	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public void SetEnregistrement(IEspaceTravail p_EspaceTravail) {
		this.m_Enregistrement = p_EspaceTravail.GetCopie();
	}

}
