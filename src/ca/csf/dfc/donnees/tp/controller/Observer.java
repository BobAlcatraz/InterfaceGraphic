package ca.csf.dfc.donnees.tp.controller;

import ca.csf.dfc.donnees.tp.model.IEspaceTravail;

public class Observer implements IObserver{
	private IEspaceTravail m_Enregistrement;
	
	/**
	 *Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IDessin#Comparer(IEspaceTravail p_EspaceTravail)
	 */
	@Override
	public boolean Comparer(IEspaceTravail p_EspaceTravail) {
		return this.m_Enregistrement.equals(p_EspaceTravail);
	}

	/**
	 *Redéfinition
	 * @see ca.csf.dfc.donnees.tp.controller.IDessin#SetEnregistrement(IEspaceTravail p_EspaceTravail) 
	 */
	@Override
	public void SetEnregistrement(IEspaceTravail p_EspaceTravail) {
		this.m_Enregistrement = p_EspaceTravail.GetCopie();
	}

}
