import java.awt.*;

public interface IForme extends Comparable<IForme>{

	
	public Boolean isclicked();	
	public void Deplacer(int x, int y);
	public void Modifier(int p_Largeur, int p_Hauteur);
	public void GetForme();
	
	public void GetX();
	public void GetY();
	public void GetHauteur();
	public void GetLarger();
	public void GetTrait();
	public void GetColor();
	public void GetRemplissage();
	
	
	
	
}
