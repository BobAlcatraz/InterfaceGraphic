import java.awt.*;

public interface IForme extends Comparable<IForme>{

	
	public Boolean isclicked();	
	public void Deplacer(int x, int y);
	public void Modifier(int p_Largeur, int p_Hauteur);
	public void GetForme();
	
	public int GetX();
	public int GetY();
	public int GetHauteur();
	public int GetLarger();
	public int GetTrait();
	public Color GetColor();
	public Color GetRemplissage();
	
	
	
	
}
