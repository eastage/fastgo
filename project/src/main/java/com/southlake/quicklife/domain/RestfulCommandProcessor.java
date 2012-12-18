package com.southlake.quicklife.domain;

import java.io.StringWriter;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
 * This is the generic class used to process restful commands
 */
public abstract class RestfulCommandProcessor {
private Document domDoc;
	
	public RestfulCommandProcessor() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder(); 
		this.domDoc = builder.newDocument(); 
	}
	
	// Restful command type of http get request
	public RestfulCommandType getGetRequestType(String cmdStr){
		RestfulCommandType type = RestfulCommandType.INVALID_POST_GET;
		
		if(cmdStr.compareTo("all-category") == 0)
			type = RestfulCommandType.GET_ALL_CATEGORY;
		
		if(cmdStr.compareTo("all-community") == 0)
			type = RestfulCommandType.GET_ALL_COMMUNITY;
		
		if(cmdStr.compareTo("category-items") == 0)
			type = RestfulCommandType.GET_CATEGORY_ITEMS;
		
		if(cmdStr.compareTo("community-info") == 0)
			type = RestfulCommandType.GET_COMMUNITY_INFO;
		
		return type;
	}
	
	// Restful command type of http post request
	public RestfulCommandType getPostRequestType(String cmdStr){
		return getGetRequestType(cmdStr);
	}
	
	// This is only the default implementation
	public String doRestfulCommand(RestfulCommandType type, String httpBody)
	{
		NodeList nodeList;
		
		//nodeList = domDoc.getChildNodes();
		
		
		System.out.println("[RestfulFacade.doRestfulCommand] restful type is: " + type.toString());
		if(type.compareTo(RestfulCommandType.GET_ALL_CATEGORY) == 0){
			//return new String("<all>\r\n<foods /><beverage />\r\n</all>");
			try {
				String xmldata = getAllCategory();
				
				System.out.println(xmldata);
				return xmldata;
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type.compareTo(RestfulCommandType.GET_CATEGORY_ITEMS) == 0){
			// in body, there should be text like <category name="foods"></category>
			// response like: "noodle", 20, 28, "images/foods/reganmian.jpg"
			
			return new String("<xmldata><foods><面条 remain='20' price='12.5' thumbnail='images/foods/reganmian.jpg'></面条></foods></xmldata>");

		}
		
		if(type.compareTo(RestfulCommandType.GET_ALL_COMMUNITY) == 0){
			return new String("<xmldata><保利蓝海郡></保利蓝海郡><南湖半岛></南湖半岛></xmldata>");
		}
		
		if(type.compareTo(RestfulCommandType.GET_COMMUNITY_INFO) == 0){
			// in body, there should be text like <community name="保利蓝海郡"></community>
			
			return new String("<xmldata>" +
					"<六号楼 units='1'>" +
						"<一单元 unitNo='1'>" +
							"01~30,4" +
						"</一单元>" +
					"</六号楼>" +
					"</xmldata>");
		}
		
		return new String("<xmldata></xmldata>");
	}
	
	private String domToStr(Document dom) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
        Transformer transformer = transformerFactory.newTransformer(); 
        
        System.out.println("[RestfulFacade.domToStr] in. ");
        
        Properties properties = transformer.getOutputProperties(); 
        properties.setProperty(OutputKeys.ENCODING,   "UTF-8"); 
        properties.setProperty(OutputKeys.METHOD,   "xml"); 
        properties.setProperty(OutputKeys.VERSION,   "1.0"); 
        properties.setProperty(OutputKeys.INDENT,   "no"); 
        transformer.setOutputProperties(properties); 

        System.out.println("[RestfulFacade.domToStr] process. " + domDoc.toString());
        
        System.out.println("[RestfulFacade.domToStr] process. " + domDoc.getElementsByTagName("all"));
		System.out.println("[RestfulFacade.domToStr] process. " + domDoc.getElementsByTagName("foods").item(0).getNodeName());
		System.out.println("[RestfulFacade.domToStr] process. " + domDoc.getElementsByTagName("beverage").item(0).getNodeName());
		
        StringWriter sw = new StringWriter();
        DOMSource domSource = new DOMSource(dom);
        StreamResult streamResult = new StreamResult(sw);
        
        transformer.transform(domSource, streamResult); 
        
        System.out.println("[RestfulFacade.domToStr] out. ");
        return sw.toString(); 
	}
	
	/*private void removeAllNodes(NodeList nodelist)
	{
	    for(int i=0; i<nodeList.getLength(); i++){
	      Node childNode = nodeList.item(i);
	      if (childNode.getNodeName() == "person") {
	         //do something with it
	      }

	      NodeList children = childNode.getChildNodes();
	      if (children != null)
	      {
	         doSomethingWithAll(children);
	      }
	    }
	}*/
	
	private String getAllCategory() throws TransformerException
	{
		Element element;
		Element all;
		
		System.out.println("[RestfulFacade.getAllCategory] in. ");
		
		if(domDoc.getElementsByTagName("xmldata").getLength() > 0){
			System.out.println("[RestfulFacade.getAllCategory] domDoc was ready, return. len: " + domDoc.getElementsByTagName("all").getLength());
			return domToStr(domDoc);
		}
		
		all = domDoc.createElement("xmldata");
		domDoc.appendChild(all);
		
		element = domDoc.createElement("foods");
		all.appendChild(element);
		
		element = domDoc.createElement("beverage");
		all.appendChild(element);
		System.out.println("[RestfulFacade.getAllCategory] process. " + domDoc.getElementsByTagName("xmldata"));
		System.out.println("[RestfulFacade.getAllCategory] process. " + domDoc.getElementsByTagName("foods").item(0).getNodeName());
		System.out.println("[RestfulFacade.getAllCategory] process. " + domDoc.getElementsByTagName("beverage").item(0).getNodeName());
		System.out.println("[RestfulFacade.getAllCategory] process. " + all.toString());
		
		System.out.println("[RestfulFacade.getAllCategory] end. ");
		return domToStr(domDoc);
	}
	
	/*
	private String getItemsByCategory(String category)
	{
		 
	}
	*/
}
