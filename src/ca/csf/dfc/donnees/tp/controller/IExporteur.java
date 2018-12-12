package ca.csf.dfc.donnees.tp.controller;

import ca.csf.dfc.donnees.tp.model.IEspaceTravail;

/**
 * Interface pour les exporteurs d'espaces de travail.
 * @author JBrazeau
 *
 */
public interface IExporteur {
	public void Exporter(IEspaceTravail p_EspaceTravail);
}
