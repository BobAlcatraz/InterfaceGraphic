package ca.csf.dfc.donnees.tp.controller;
import ca.csf.dfc.donnees.tp.model.*;

/**
 * @author JBrazeau
 *
 */
public interface IEnregistrement {
	public void Enregistrer(IEspaceTravail p_EspaceAEnregistrer);
	public EspaceTravail Charger();
}
