/**
 * 
 */
package ca.csf.dfc.donnees.tp.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import ca.csf.dfc.donnees.tp.model.*;

/**
 * @author JBrazeau
 *
 */
public class ExporteurSVG implements IExporteur{
	static private ExporteurSVG m_Instance = null;
	
	private ExporteurSVG() {
		ExporteurSVG.m_Instance = this;
	}
	
	static public ExporteurSVG getInstance() {
		if(ExporteurSVG.m_Instance == null) {
			new ExporteurSVG();
		}
		
		return ExporteurSVG.m_Instance;
	}
	
	// EXPORTAGE
	public void Exporter(IEspaceTravail p_EspaceTravail) 
	{
		PrintWriter doc = null;
		try 
		{
			Integer hauteurEspace = p_EspaceTravail.getHauteur();
			Integer largeurEspace = p_EspaceTravail.getLargeur();
			
			doc = creationPrintWriterSVGParJFileChooser();
			doc.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
			doc.println("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \r\n" + 
					"  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
			doc.println("<svg height=\""+ hauteurEspace +"\" width=\"" + largeurEspace +"\" version=\"1.1\" " + 
					"xmlns=\"http://www.w3.org/2000/svg\">");
			
			ecritureFormatSVGDesFormes(doc, p_EspaceTravail);
			
			doc.println("</svg>");	
		}
		catch(IOException exp) 
		{
			System.err.println("Erreur d'écriture : " + exp);
		}
		finally
		{
			if(doc != null) {
				doc.close();
			}
		}
	}
	
	private PrintWriter creationPrintWriterSVGParJFileChooser() throws IOException 
	{
		PrintWriter fileWriter = null;
    	JFileChooser chooser = new JFileChooser();
    		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichiers svg (*.svg)","svg");
    		chooser.setFileFilter(filtre);
    		
    	// Windows
    	/*  chooser.setCurrentDirectory(new File("/home/%username%/Documents"));
   		int valRetournee = chooser.showOpenDialog(null); */
    		
    	int valRetournee = chooser.showSaveDialog(chooser.getParent());
    	
    	if (valRetournee == JFileChooser.APPROVE_OPTION) 
    	{
    		String nomFichier = chooser.getSelectedFile().toString();
    		if(!nomFichier.endsWith(".svg")){
    			nomFichier += ".svg";
    		}
    		
    	     fileWriter = new PrintWriter(new File(nomFichier), "UTF-8");
    	}
    	
    	return fileWriter;
	}

	private void ecritureFormatSVGDesFormes(PrintWriter p_Doc, IEspaceTravail p_EspaceTravail) {
		
		for(IForme forme: p_EspaceTravail) {

			if(forme instanceof Oval) 

			{
				ecritureFormatSVGDeOvale(p_Doc, (Oval)forme);
			}
			else if(forme instanceof Rectangle) 
			{
				ecritureFormatSVGDeRectangle(p_Doc, (Rectangle)forme);
			}
			else if(forme instanceof IForme) 
			{
				ecritureFormatSVGDeLigne(p_Doc, forme);
			}
		}
	}

	private void ecritureFormatSVGDeOvale(PrintWriter p_Doc, Oval p_Ovale) {
		Integer cx = p_Ovale.GetX(); //test
		Integer cy = p_Ovale.GetY();
		Integer rx = p_Ovale.GetLargeur()/2;
		Integer ry = p_Ovale.GetHauteur()/2;
		//Style (couleurs rgb)
		Integer fillRed     = p_Ovale.GetRemplissage().getRed(); // Ce serait pertinant d'avoir ces fonctions directement dans forme. (respect des regles)
		Integer fillGreen   = p_Ovale.GetRemplissage().getGreen();
		Integer fillBlue    = p_Ovale.GetRemplissage().getBlue();
		Integer strokeRed   = p_Ovale.GetCouleur().getRed();
		Integer strokeGreen = p_Ovale.GetCouleur().getGreen();
		Integer strokeBlue  = p_Ovale.GetCouleur().getBlue();
		Integer strokeWidth = p_Ovale.GetTrait();
		
		p_Doc.println("	<ellipse cx=\""+ cx +"\" cy=\""+ cy +"\" rx=\""+ rx +"\" ry=\""+ ry +"\"\r"
				    + " style=\"fill:rgb("+ fillRed +", "+ fillGreen +", "+ fillBlue+ ");"
				    		 + "stroke:rgb("+ strokeRed +", "+ strokeGreen +", "+ strokeBlue +");"
				    		 + "stroke-width:"+ strokeWidth +"\" />");
	}
	
	private void ecritureFormatSVGDeRectangle(PrintWriter p_Doc, Rectangle p_Rectangle) {
		Integer x      = p_Rectangle.GetX(); 
		Integer y      = p_Rectangle.GetY();
		Integer width  = p_Rectangle.GetLargeur();
		Integer height = p_Rectangle.GetHauteur();
		//Style (couleurs rgb)
		Integer fillRed     = p_Rectangle.GetRemplissage().getRed(); 
		Integer fillGreen   = p_Rectangle.GetRemplissage().getGreen();
		Integer fillBlue    = p_Rectangle.GetRemplissage().getBlue();
		Integer strokeRed   = p_Rectangle.GetCouleur().getRed();
		Integer strokeGreen = p_Rectangle.GetCouleur().getGreen();
		Integer strokeBlue  = p_Rectangle.GetCouleur().getBlue();
		Integer strokeWidth = p_Rectangle.GetTrait();
		
		
		p_Doc.println("	<rect x=\""+ x +"\" y=\""+ y +"\" width=\""+ width +"\" height=\""+ height +"\" "
				     + " style=\"fill:rgb("+ fillRed +", "+ fillGreen +", "+ fillBlue+ ");"
		    		          + "stroke:rgb("+ strokeRed +", "+ strokeGreen +", "+ strokeBlue +");"
		    		          + "stroke-width:"+ strokeWidth +"\" />");
	}
	
	private void ecritureFormatSVGDeLigne(PrintWriter p_Doc, IForme p_Ligne) {
		Integer x1 = p_Ligne.GetX();
		Integer y1 = p_Ligne.GetY();
		Integer x2 = x1 + p_Ligne.GetLargeur();
		Integer y2 = y1 + p_Ligne.GetHauteur();
		//Style
		Integer strokeRed   = p_Ligne.GetCouleur().getRed();
		Integer strokeGreen = p_Ligne.GetCouleur().getGreen();
		Integer strokeBlue  = p_Ligne.GetCouleur().getBlue();
		Integer strokeWidth = p_Ligne.GetTrait();
		
		p_Doc.println("	<line x1=\""+ x1 +"\" y1=\""+ y1 +"\" x2=\""+ x2 +"\" y2=\""+ y2 +"\" "
				    + " style=\"stroke:rgb(\"+ strokeRed +\", \"+ strokeGreen +\", \"+ strokeBlue +\");" 
				    +  "stroke-width:"+ strokeWidth +"\" />");
	}


}
