package utilities;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
import java.util.ArrayList;


public class SystemUtil_SW
{
	public static String[] xmlMsg = loadAllXMLMessages();
	
	private static String getXmlFilePath() {
			return "C:/SWTravel/SWTravel_Tests/src/utilities/SW_msgs.xml";
		}
	
	
	public static String getXmlMsg(int msgId) {
		return xmlMsg[msgId];
	}
	
    public static void assertFalse(boolean assertFalse)
	{
		org.junit.Assert.assertFalse(assertFalse);
	}
    
	public static void assertTrue(boolean assertTrue)
	{
		org.junit.Assert.assertTrue(assertTrue);
	}

	
	//=================================================================================================================
	// PRIVATE METHODS
	//=================================================================================================================
	private static String[] loadAllXMLMessages() {
		try {
			File fXmlFile = new File(getXmlFilePath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("MESSAGE");
			ArrayList<String> xmlMessages = new ArrayList<String>();
			int id;
			for (int index = 0; index < nodeList.getLength();  index++) {
				Node node = nodeList.item(index);
				Element element = (Element) node;
				id = Integer.parseInt(element.getElementsByTagName("ID").item(0).getTextContent().trim());
				
				if(id > xmlMessages.size())
					xmlMessages = increaseCapacity(id + 1, xmlMessages);
				
				xmlMessages.add(id,element.getElementsByTagName("MSG").item(0).getTextContent().trim());
			}		
			return xmlMessages.toArray(new String[xmlMessages.size()]);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ArrayList<String> increaseCapacity(int newSize, ArrayList<String> xmlMessages) {
		
		for(int i = 0; i < newSize; i++)
			if(i >= xmlMessages.size() || xmlMessages.get(i) == null)
				xmlMessages.add(i,"");
		return xmlMessages;
	}
}
	
		