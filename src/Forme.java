import java.awt.*;

abstract class Forme implements IForme {
	
	protected int m_X; 
	protected int m_Y;	
	protected int m_Hauteur;	
	protected int m_Largeur;	
	protected int m_Trait;	
	protected Color m_Couleur;	
	protected Color m_Remplissage;
	
	protected abstract void setX(int p_X);
	protected abstract void setY(int p_Y);
	protected abstract void setHauteur(int p_Hauteur);
	protected abstract void setLargeur(int p_Largeur);
	protected abstract void setTrait(int p_Trait);
	protected abstract void setCouleur(Color p_Couleur);
	protected abstract void setRemplissage(Color p_Remplissage);
	
	
	

}
