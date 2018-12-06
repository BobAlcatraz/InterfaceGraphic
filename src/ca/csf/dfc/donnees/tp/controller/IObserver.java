package ca.csf.dfc.donnees.tp.controller;

import ca.csf.dfc.donnees.tp.model.IEspaceTravail;

public interface IObserver {
	boolean Comparer(IEspaceTravail p_EspaceTravail);
	public void SetEnregistrement(IEspaceTravail p_EspaceTravail);
}