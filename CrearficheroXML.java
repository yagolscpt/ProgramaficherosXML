package beaejerciciosrepasoexamen;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class CrearficheroXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int plazaocupada;
		 String marca;
		 String modelo;
		 int matricula;
		 
		 DocumentBuilderFactory dbf = null;
			
			DocumentBuilder db = null;
			
			// instanciamos
			dbf = DocumentBuilderFactory.newInstance();
			try {
				
				db = dbf.newDocumentBuilder();
				DOMImplementation di = db.getDOMImplementation();
				
				Document documento = di.createDocument(null, "Parking", null);
				
				documento.setXmlVersion("1.0");
				
				for (int i = 0; i < 3; i++) {
					plazaocupada = Integer.parseInt(IntroducirDatos.introducirDatos("Plaza ocupada: "));
					marca = IntroducirDatos.introducirDatos("Marca: ");
					modelo = IntroducirDatos.introducirDatos("Modelo: ");
					matricula= Integer.parseInt(IntroducirDatos.introducirDatos("Matricula: "));
					
					Element raiz = documento.createElement("alumno");
					documento.getDocumentElement().appendChild(raiz);
					
					crearElemento("plazaocupada", Integer.toString(plazaocupada), raiz, documento);
					crearElemento("Marca", marca, raiz, documento);
					crearElemento("modelo", modelo, raiz, documento);
					crearElemento("matricula", Integer.toString(matricula), raiz, documento);
					
				}
				Source fuente = new DOMSource(documento);
				
				File f = new File("Parking.xml");
				
				Result resultado = new StreamResult(f);
				
				Transformer transformer = null;
				
					transformer = TransformerFactory.newInstance().newTransformer();
					transformer.transform(fuente,resultado);

					
					//ver  el fichero en consola
					
					Result consola = new StreamResult(System.out);
					
					transformer.transform(fuente, consola);	
	}catch (Exception e) {
		e.printStackTrace();
	}
			
}
	private static void crearElemento(String datos, String valor, Element raiz, Document documento) {
		
		Element elemento = documento.createElement(datos);
		
		Text texto = documento.createTextNode(valor);
		raiz.appendChild(elemento);
		elemento.appendChild(texto);
	}

}