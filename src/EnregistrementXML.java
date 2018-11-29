import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author JBrazeau
 *
 */
public class EnregistrementXML implements IEnregistrement {
	//Éléments
	private final static String ELM_ESPACE_TRAVAIL = "espace";
    private final static String ELM_FORME          = "forme";
    
    //Attributs élément: ELM_ESPACE_TRAVAIL
    private final static String ATTR_HAUTEUR_ESP = "ehauteur";
    private final static String ATTR_LARGEUR_ESP = "elargeur";
    
    //Attributs élément: ELM_FORME
    private final static String ATTR_TYPE              = "type";
    private final static String ATTR_COOR_X            = "coorx";
    private final static String ATTR_COOR_Y            = "coory"; 
    private final static String ATTR_HAUTEUR_FORME     = "fhauteur";
    private final static String ATTR_LARGEUR_FORME     = "flargeur";
    private final static String ATTR_EPAISSEUR_CONTOUR = "epaisseur";
    private final static String ATTR_COULEUR_CONTOUR   = "coulcontour";
    private final static String ATTR_COULEUR_FOND      = "coulfond";
    
    //Conformité des formes
    private final static String FORME_TYPE_RECTANGLE = "rectangle";
    private final static String FORME_TYPE_LIGNE     = "ligne";
    private final static String FORME_TYPE_OVALE     = "ovale";
    
	public void Enregistrer(IEspaceTravail p_EspaceAEnregistrer) {
		
	}
	
	public EspaceTravail Charger() {
		
		EspaceTravail espaceTravail = null;
		int largeurEspace = 0;
		int hauteurEspace = 0;	
		List<Forme> listeFormes = new ArrayList<Forme>();
		
		
		try { 
			XMLStreamReader doc = null;
			FileReader input = creationReaderParJFileChooser();
			doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
			
			doc.next();
			
			//Est-ce un espace de travail?
			if (!doc.getLocalName().equals(ELM_ESPACE_TRAVAIL)) 
			{ 
				throw new XMLStreamException("Oups, ce n'est pas le bon élément racine: " + doc.getLocalName());
			}
			
			doc.next();
			
			//Chargement des formes
			while (doc.isStartElement() && doc.getLocalName().equals(ELM_FORME))
	        {
	            chargerFormeDansListe(doc, listeFormes);
	        }
			
		}
		catch(XMLStreamException exp) {
			System.err.println(exp);
		}
		catch(FileNotFoundException exp) {
			System.err.println("Erreur lors de la sélection du document:" + exp);
		}
		
		return null;
	}
	
	
	private FileReader creationReaderParJFileChooser() throws FileNotFoundException {
		FileReader fileReader = null;
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("xml");
		    chooser.setFileFilter(filtre);
		    int valRetournee = chooser.showOpenDialog(chooser.getParent());
		
		if(valRetournee == JFileChooser.APPROVE_OPTION) {
			fileReader = new FileReader(new File(chooser.getSelectedFile().getPath()));
		 }
		
		return fileReader;
	}
	
	private void chargerFormeDansListe(XMLStreamReader p_doc) { // Test par String, a changer pour ADD.
		
		try 
		{
		Forme forme;	
			
		String typeForme      =                  p_doc.getAttributeValue("", ATTR_TYPE);
		int coorX             = Integer.parseInt(p_doc.getAttributeValue("", ATTR_COOR_X));
		int coorY             = Integer.parseInt(p_doc.getAttributeValue("", ATTR_COOR_Y));
		int hauteur           = Integer.parseInt(p_doc.getAttributeValue("", ATTR_HAUTEUR_FORME));
		int largeur           = Integer.parseInt(p_doc.getAttributeValue("", ATTR_LARGEUR_FORME));
		int epaisseurContour  = Integer.parseInt(p_doc.getAttributeValue("", ATTR_EPAISSEUR_CONTOUR));
		int numCouleurContour = Integer.parseInt(p_doc.getAttributeValue("", ATTR_COULEUR_CONTOUR));
		int numCouleurFond    = Integer.parseInt(p_doc.getAttributeValue("", ATTR_COULEUR_FOND));
		
		Color couleurContour = new Color(numCouleurContour);
		Color couleurFond    = new Color(numCouleurFond);
		
		switch(typeForme){
			case FORME_TYPE_LIGNE:
				break;
			case FORME_TYPE_OVALE:
				break;
			case FORME_TYPE_RECTANGLE:
				break;
		
		}
		catch(NumberFormatException exp) {
			System.err.println("Tentative de conversion en Int écouchée :" + exp);
		}
		catch(Exception exp) {
			System.err.println("Tentative de chargement d'une forme échouée :" + exp);
		}
		

	}
	
		
	}
}
