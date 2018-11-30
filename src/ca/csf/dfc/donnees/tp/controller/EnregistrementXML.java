package ca.csf.dfc.donnees.tp.controller;

import java.util.*;
import java.util.List;
import java.awt.Color;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.*;

import ca.csf.dfc.donnees.tp.model.*;

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
    private final static String ATTR_EPAISSEUR_TRAIT = "epaisseur";
    private final static String ATTR_COULEUR_CONTOUR   = "coulcontour";
    private final static String ATTR_COULEUR_REMPLISSAGE      = "coulfond";
    
    
	// ENREGISTREMENT
    public void Enregistrer(IEspaceTravail p_EspaceAEnregistrer) 
    {
        XMLStreamWriter doc = null;

        try
        {
        	String largeurEspace = Integer.toString(p_EspaceAEnregistrer.getLargeur());
        	String hauteurEspace = Integer.toString(p_EspaceAEnregistrer.getHauteur());
        	

            FileWriter output = creationFileWriterParJFileChooser();

            doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);

            // <?xml version="1.0" ?>
            doc.writeStartDocument();
            // <espacetravail largeur= hauteur=>
            doc.writeStartElement( ELM_ESPACE_TRAVAIL );
            	doc.writeAttribute( ATTR_LARGEUR_ESP, largeurEspace );
            	doc.writeAttribute( ATTR_HAUTEUR_ESP, hauteurEspace );

            // Écriture des formes...
            enregistrementFormes(doc, p_EspaceAEnregistrer);
            
            // </espacetravail>
            doc.writeEndElement();
            doc.writeEndDocument();

        }
        catch (IOException exp)
        {
            System.err.println("Erreur d'ecriture : " + exp);

        }
        catch (XMLStreamException exp)
        {
            System.err.println("Erreur dans le XML : " + exp);

        }
        finally
        {
            if (doc != null)
            {
                try
                {
                    doc.flush(); 
                    doc.close();

                }
                catch (XMLStreamException exp)
                {
                    System.err.println("Erreur lors de la fermeture" + exp);
                }
                doc = null;
            }
        }
	}
    
    private FileWriter creationFileWriterParJFileChooser() throws IOException {
    	FileWriter fileWriter = null;
    	
    	JFileChooser chooser = new JFileChooser();
    		chooser.setCurrentDirectory(new File("/home/%username%/Documents"));
    		FileNameExtensionFilter filtre = new FileNameExtensionFilter("xml");
    		chooser.setFileFilter(filtre);
    		
    	int valRetournee = chooser.showSaveDialog(null);
    	
    	if (valRetournee == JFileChooser.APPROVE_OPTION) 
    	{
    	     fileWriter = new FileWriter(chooser.getSelectedFile() + ".xml");
    	}
    	
    	return fileWriter;
    }
    
    private void enregistrementFormes(XMLStreamWriter p_Doc, IEspaceTravail p_EspaceAEnregistrer) {
    	for(Forme forme: p_EspaceAEnregistrer.getListeFormes()) 
    	{
    		String coorX              = Integer.toString(forme.GetX());
    		String coorY              = Integer.toString(forme.GetY());
    		String hauteurForme       = Integer.toString(forme.GetHauteur());
    		String largeurForme       = Integer.toString(forme.GetLarger());
    		String epaisseurTrait     = Integer.toString(forme.GetTrait());
    		String couleurContour     = forme.GetCouleur().toString();
    		String couleurRemplissage = forme.GetRemplissage().toString();
    		
    		
    		p_Doc.writeStartElement(ELM_FORME);
    		 
        	p_Doc.writeAttribute( ATTR_TYPE,                forme.GetForme()      );
    		p_Doc.writeAttribute( ATTR_COOR_X,              coorX                 ); 
        	p_Doc.writeAttribute( ATTR_COOR_Y,              coorY                 );
        	p_Doc.writeAttribute( ATTR_LARGEUR_FORME,       largeurForme          );
        	p_Doc.writeAttribute( ATTR_HAUTEUR_FORME,       hauteurForme          );
        	p_Doc.writeAttribute( ATTR_EPAISSEUR_TRAIT,     epaisseurTrait        );
        	p_Doc.writeAttribute( ATTR_COULEUR_CONTOUR,     couleurContour        );
        	p_Doc.writeAttribute( ATTR_COULEUR_REMPLISSAGE, couleurRemplissage    );
        	
        	p_Doc.writeEndElement();
    	}
    }
	
    
	// CHARGEMENT
	public void Charger(IEspaceTravail p_EspaceActuelEcrase) {	
		XMLStreamReader doc = null;
		
		try { 
			
			FileReader input = creationFileReaderParJFileChooser();
			doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
			
			doc.next();
			
			//Est-ce un espace de travail?
			if (!doc.getLocalName().equals(ELM_ESPACE_TRAVAIL)) 
			{ 
				throw new XMLStreamException("Oups, ce n'est pas le bon élément racine: " + doc.getLocalName());
			}
			
			chargerDimensionEspace(doc, p_EspaceActuelEcrase);
	        chargerFormesDansEspace(doc, p_EspaceActuelEcrase);
			
		}
		catch(XMLStreamException exp) {
			System.err.println(exp);
		}
		catch(FileNotFoundException exp) {
			System.err.println("Erreur lors de la sélection du document:" + exp);
		}
		catch(NumberFormatException exp) {
			System.err.println("Erreur lors du chargement d'un attribut en int:" + exp);
		}
		finally {
			if (doc != null)
            {
                try
                {
                    doc.close();
                }
                catch (XMLStreamException exp)
                {
                    System.err.println("Erreur lors de la fermeture" + exp);
                }
                doc = null;
            }
		}
	}
	
	private FileReader creationFileReaderParJFileChooser() throws FileNotFoundException {
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
	
	private void chargerDimensionEspace(XMLStreamReader p_Doc, IEspaceTravail p_EspaceTravail) throws XMLStreamException, NumberFormatException 
	{
		int largeur = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_LARGEUR_ESP));
		int hauteur = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_HAUTEUR_ESP));
			
		p_EspaceTravail.setTaille(largeur, hauteur);
		p_Doc.next();
	}
	
	private void chargerFormesDansEspace(XMLStreamReader p_Doc, IEspaceTravail p_EspaceTravail) throws NumberFormatException { // Test par String, a changer pour ADD.
		
		while (p_Doc.isStartElement() && p_Doc.getLocalName().equals(ELM_FORME))
        {
			Forme formeAjoute;	
			
			String typeForme      =                  p_Doc.getAttributeValue("", ATTR_TYPE);
			int coorX             = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_COOR_X));
			int coorY             = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_COOR_Y));
			int hauteur           = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_HAUTEUR_FORME));
			int largeur           = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_LARGEUR_FORME));
			int trait             = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_EPAISSEUR_TRAIT));
			int numCouleurContour = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_COULEUR_CONTOUR));
			int numCouleurFond    = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_COULEUR_REMPLISSAGE));
		
			Color couleurContour = new Color(numCouleurContour);
			Color couleurFond    = new Color(numCouleurFond);
		
			switch(typeForme){
				case Ligne.getForme():
					formeAjoute = new Ligne(coorX, coorY, hauteur, largeur, trait, couleurContour, couleurFond);
					break;
				case Ovale.getForme():
					formeAjoute = new Ovale(coorX, coorY, hauteur, largeur, trait, couleurContour, couleurFond);
					break;
				case Rectangle.getForme():
					formeAjoute = new Rectangle(coorX, coorY, hauteur, largeur, trait, couleurContour, couleurFond);
					break;
			}
			
			if(formeAjoute != null)
			{
				p_EspaceTravail.draw(formeAjoute);
			}
			
			p_Doc.next();
		}
		
	}
}
