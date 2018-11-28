import java.awt.*;

abstract class Forme implements IForme {
	
	private int m_X; 
	private int m_Y;	
	private int m_Hauteur;	
	private int m_Largeur;	
	private int m_Trait;	
	private Color m_Couleur;	
	private Color m_Remplissage;
	
	protected abstract void setX();
	protected abstract void setY();
	protected abstract void setHauteur();
	protected abstract void setLargeur();
	protected abstract void setTrait();
	protected abstract void setCouleur();
	protected abstract void setRemplissage();
	
	
	

}
