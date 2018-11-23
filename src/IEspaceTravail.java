
public interface IEspaceTravail   {

	public void setTaille(int p_x, int p_y);
	
	public void supprimer(Iforme p_forme);
	
	public Iforme verifierClick();
	
	public void draw(Iforme p_forme);
}
