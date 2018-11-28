import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/**
 * @author JBrazeau
 *
 */
public class EnregistrementXML implements IEnregistrement {
	
	public void Enregistrer(IEspaceTravail p_EspaceAEnregistrer) {
		
	}
	
	public IEspaceTravail Charger() {
		
		try {
			XMLStreamReader doc = null;

			FileReader input = SelectionFileChooser();
			doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
		}
		catch(Exception exp) {
			
		}
		
		return null;
	}
	
	private FileReader SelectionFileChooser() {
		FileReader fileReader = null;
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("xml");
		    chooser.setFileFilter(filtre);
		    int valRetournee = chooser.showOpenDialog(chooser.getParent());
		
		if(valRetournee == JFileChooser.APPROVE_OPTION) {
			try{
				fileReader = new FileReader(new File(chooser.getSelectedFile().getPath()));
			}
			catch(Exception exp){
				System.err.println("Erreur lors de sélection du fichier XML: " + exp);
			}
		 }
		
		return fileReader;
	}
}
