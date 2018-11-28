/**
 * @author JBrazeau
 *
 */
public interface IEnregistrement {
	public void Enregistrer(IEspaceTravail p_EspaceAEnregistrer);
	public IEspaceTravail Charger();
}
