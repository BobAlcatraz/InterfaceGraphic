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
 * Classe permettant l'enregistrement et le chargement d'un espace de travail sous format XML
 * @author JBrazeau
 * 
 */
public class EnregistrementXML implements IEnregistrement {
	static private EnregistrementXML m_Instance = null; 
	
	//Éléments
	private final static String ELM_ESPACE_TRAVAIL = "espace";
    private final static String ELM_FORME          = "forme";
    
    //Attributs élément: ELM_ESPACE_TRAVAIL
    private final static String ATTR_HAUTEUR_ESP = "ehauteur";
    private final static String ATTR_LARGEUR_ESP = "elargeur";
    
    //Attributs élément: ELM_FORME
    private final static String ATTR_TYPE                = "type";
    private final static String ATTR_COOR_X              = "coorx";
    private final static String ATTR_COOR_Y              = "coory"; 
    private final static String ATTR_HAUTEUR_FORME       = "fhauteur";
    private final static String ATTR_LARGEUR_FORME       = "flargeur";
    private final static String ATTR_EPAISSEUR_TRAIT     = "epaisseurtrait";
    private final static String ATTR_COULEUR_TRAIT       = "coultrait";
    private final static String ATTR_COULEUR_REMPLISSAGE = "coulremplissage";
    
    /**
     * Constructeur par défaut.
     * Associe l'instance à m_Instance.
     */
    private EnregistrementXML(){
    	EnregistrementXML.m_Instance = this;
    }
    
    /**
     * Retourne l'instance existante ou nouvellement créée.
     * @return l'instance
     */
    static public EnregistrementXML getInstance() {
    	if(EnregistrementXML.m_Instance == null) {
    		new EnregistrementXML();
    	}
    	
    	return EnregistrementXML.m_Instance;
    }
    
	// ENREGISTREMENT
    
    /**
     * Enregistre un espace de travail dans un fichier format xml.
     * @param p_EspaceAEnregistrer l'espace de travail 
     */
    public void Enregistrer(IEspaceTravail p_EspaceAEnregistrer) 
    {
        XMLStreamWriter doc = null;

        try
        {
        	String largeurEspace = Integer.toString(p_EspaceAEnregistrer.getLargeur());
        	String hauteurEspace = Integer.toString(p_EspaceAEnregistrer.getHauteur());

            FileWriter output = creationFileWriterXMLParJFileChooser();

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
        catch (NullPointerException exp) 
        {
        	System.err.println("Erreur, référence inexistante : " + exp);
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
    
    /**
     * Permet d'avoir un FileWriter avec l'extension xml par l'entremise d'un JFileChooser
     * @return Le FileWriter
     * @throws IOException Si le JFIleChooser est annulé ou le nom de fichier est d'un format inadéquat.
     */
    private FileWriter creationFileWriterXMLParJFileChooser() throws IOException {
    	FileWriter fileWriter = null;
    	
    	JFileChooser chooser = new JFileChooser();
    		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichiers xml (*.xml)","xml");
    		chooser.setFileFilter(filtre);
    	
    	// Windows
    	/*chooser.setCurrentDirectory(new File("/home/%username%/Documents"));*	
    	int valRetournee = chooser.showSaveDialog(null);*/
 
    	int valRetournee = chooser.showSaveDialog(chooser.getParent());
    	
    	if (valRetournee == JFileChooser.APPROVE_OPTION) 
    	{	
    		String nomFichier = chooser.getSelectedFile().toString();
    		if(!nomFichier.endsWith(".xml")) {
    			nomFichier += ".xml";
    		}
    	    
    		fileWriter = new FileWriter(nomFichier);
    	}
    	
    	return fileWriter;
    }
    
    /**
     * Enregistre les formes pour Enregistrer().
     * @param p_Doc Le doc dans lequel l'enregistrement est en cours.
     * @param p_EspaceAEnregistrer L'espace de travail enregistré.
     * @throws XMLStreamException Si l'état du stream n'est pas approprié à l'écriture souhaité.
     */
    private void enregistrementFormes(XMLStreamWriter p_Doc, IEspaceTravail p_EspaceAEnregistrer) throws XMLStreamException {
    	for(IForme forme: p_EspaceAEnregistrer) 
    	{
    		Integer coorX                 = forme.GetX();
    		Integer coorY                 = forme.GetY();
    		Integer hauteurForme          = forme.GetHauteur();
    		Integer largeurForme          = forme.GetLargeur();
    		Integer epaisseurTrait        = forme.GetTrait();
    		Integer couleurRGBContour     = forme.GetCouleur().getRGB();
    		Integer couleurRGBRemplissage = null;
    		if(forme.GetRemplissage() != null) {
    			couleurRGBRemplissage = forme.GetRemplissage().getRGB();
    		}
    		
    			
    		
    		p_Doc.writeStartElement(ELM_FORME);
    		 
        	p_Doc.writeAttribute( ATTR_TYPE,                forme.GetForme()                 );
    		p_Doc.writeAttribute( ATTR_COOR_X,              coorX.toString()                 ); 
        	p_Doc.writeAttribute( ATTR_COOR_Y,              coorY.toString()                 );
        	p_Doc.writeAttribute( ATTR_LARGEUR_FORME,       largeurForme.toString()          );
        	p_Doc.writeAttribute( ATTR_HAUTEUR_FORME,       hauteurForme.toString()          );
        	p_Doc.writeAttribute( ATTR_EPAISSEUR_TRAIT,     epaisseurTrait.toString()        );
        	p_Doc.writeAttribute( ATTR_COULEUR_TRAIT,       couleurRGBContour.toString()     );
        	
        	if(couleurRGBRemplissage == null) {
        		p_Doc.writeAttribute( ATTR_COULEUR_REMPLISSAGE, "NULL" );
        	} 
        	else {
        		p_Doc.writeAttribute( ATTR_COULEUR_REMPLISSAGE, couleurRGBRemplissage.toString() );
        	}
        	
        	p_Doc.writeEndElement();
    	}
    }

    
	// CHARGEMENT
    
    /**
     * Charge un espace de travail d'un fichier format xml.
     * @param p_EspaceAEnregistrer l'espace de travail 
     */
	public void Charger(IEspaceTravail p_EspaceActuelEcrase) {	
		XMLStreamReader doc = null;
		
		try { 
			
			FileReader input = creationFileReaderXMLParJFileChooser();
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
		catch(NullPointerException exp) {
			System.err.println("Erreur, référence inexistante : " + exp);
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
	
	/**
	 * Retourne un FileReader à l'aide d'un fichier choisi par JFileChooser.
	 * @return Le FileReader
	 * @throws FileNotFoundException Si le JFIleChooser est annulée ou le nom de fichier est d'un format inadéquat.
	 */
	private FileReader creationFileReaderXMLParJFileChooser() throws FileNotFoundException {
		FileReader fileReader = null;
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichiers xml (*.xml)","xml");
		    chooser.setFileFilter(filtre);
		    
		// Windows
		/*  chooser.setCurrentDirectory(new File("/home/%username%/Documents"));
		 int valRetournee = chooser.showOpenDialog(null); */
		 int valRetournee = chooser.showOpenDialog(chooser.getParent());
		
		if(valRetournee == JFileChooser.APPROVE_OPTION) {
			fileReader = new FileReader(chooser.getSelectedFile());
		 }
		
		return fileReader;
	}
	
	/**
	 * S'occupe de redimensionner l'espace de travail avec les dimensions enregistrées dans le fichier.
	 * @param p_Doc Le StreamReader du chargement en cours.
	 * @param p_EspaceTravail L'espace de travail redimensionné.
	 * @throws XMLStreamException S'il y a une erreur lors du processus de lecture du fichier.
	 * @throws NumberFormatException Si la valeur des attributs dans le fichier chargé ne sont pas numérique où nécessaire.
	 */
	private void chargerDimensionEspace(XMLStreamReader p_Doc, IEspaceTravail p_EspaceTravail) throws XMLStreamException, NumberFormatException 
	{
		Integer largeur = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_LARGEUR_ESP));
		Integer hauteur = Integer.parseInt(p_Doc.getAttributeValue("", ATTR_HAUTEUR_ESP));
			
		p_EspaceTravail.setTaille(largeur, hauteur);
		p_Doc.next();
	}
	
	/**
	 * S'occupe de charger les formes dans l'espace de travail pour un chargement en cours.
	 * @param p_Doc Le StreamReader du chargement en cours.
	 * @param p_EspaceTravail L'espace de travail devant contenir les formes.
	 * @throws NumberFormatException Si la valeur des attributs dans le fichier chargé ne sont pas numérique où nécessaire.
	 * @throws XMLStreamException S'il y a une erreur lors du processus de lecture du fichier.
	 */
	private void chargerFormesDansEspace(XMLStreamReader p_Doc, IEspaceTravail p_EspaceTravail) throws NumberFormatException, XMLStreamException { // Test par String, a changer pour ADD.
		p_EspaceTravail.Vider();
		
		while (p_Doc.isStartElement() && p_Doc.getLocalName().equals(ELM_FORME))
        {
			Forme formeAjoute = null;	
			
			String typeForme        =                  p_Doc.getAttributeValue(null, ATTR_TYPE)                  ;
			Integer coorX           = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_COOR_X)				);
			Integer coorY           = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_COOR_Y)				);
			Integer hauteur         = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_HAUTEUR_FORME)		);
			Integer largeur         = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_LARGEUR_FORME)		);
			Integer trait           = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_EPAISSEUR_TRAIT)   	);
			Integer rgbCouleurTrait = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_COULEUR_TRAIT)		);
			Color couleurTrait      = new Color(rgbCouleurTrait);
			
			Integer rgbCouleurFond  = null;
			Color couleurFond       = null;
			if(!p_Doc.getAttributeValue(null, ATTR_COULEUR_REMPLISSAGE).equals("NULL")) {
				rgbCouleurFond      = Integer.parseInt(p_Doc.getAttributeValue(null, ATTR_COULEUR_REMPLISSAGE)  );
				couleurFond         = new Color(rgbCouleurFond);
				
			}
		
			switch(typeForme) {
				case "ligne":
					formeAjoute = new Ligne(coorX, coorY, hauteur, largeur, trait, couleurTrait, couleurFond);
					break;
				case "oval":  
					formeAjoute = new Oval(coorX, coorY, hauteur, largeur, trait, couleurTrait, couleurFond);
					break;
				case "Rectangle":
					formeAjoute = new Rectangle(coorX, coorY, hauteur, largeur, trait, couleurTrait, couleurFond);
			}
			
			if(formeAjoute != null)
			{
				p_EspaceTravail.draw(formeAjoute);
			}

			p_Doc.next(); 
			p_Doc.next();
        }
		
	}
}
