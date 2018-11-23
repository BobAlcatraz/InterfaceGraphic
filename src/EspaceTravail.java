import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class EspaceTravail extends JPanel implements IEspaceTravail  {

	private ArrayList<Iforme> m_ListForme;
	
	
	public EspaceTravail(int p_x, int p_y) {
		super();
		this.setSize(p_x,p_y);
	}
	
	@Override
	public void setTaille(int p_x, int p_y) {
		this.setSize(p_x, p_y);
	}

	@Override
	public void supprimer(Iforme p_forme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iforme verifierClick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Iforme p_forme) {
		p_forme.draw();
		
	}

	
	
	private class ActionDragSouris implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	private class ActionClickSouris implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
