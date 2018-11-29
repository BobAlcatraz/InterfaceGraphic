package ca.csf.dfc.donnees.tp.view;

public interface IDessin {
	public void CreerEspaceTravail(int p_Largeur, int p_Hauteur);
	public boolean VerifierModification();
	public boolean Sauvegarder();
	public boolean Charger();
	public boolean Exporter();
	public void AjouterForme();
}
