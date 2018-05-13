package svg;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SVGWriter {
	public static void write(String folder, String cc, List<String> paths) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElementNS("http://www.w3.org/2000/svg", "svg");
			rootElement.setAttribute("viewBox", "0 0 1024 1024");
			doc.appendChild(rootElement);
			Element g = doc.createElement("g");
			rootElement.appendChild(g);
			Attr attr = doc.createAttribute("transform");
			attr.setValue("scale(1, -1) translate(0, -900)");
			g.setAttributeNode(attr);
			for (String p : paths) {
				Element path = doc.createElement("path");
				path.setAttribute("d", p);
				path.setAttribute("fill", "gray");
				g.appendChild(path);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(
					new File(folder + File.separator + "d" + cc.codePointAt(0) + ".svg"));
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
}
